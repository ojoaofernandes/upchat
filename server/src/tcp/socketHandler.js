'use strict';

const lists = require('./lists.js');
const users = require('./users.js');
const protocol = require('./protocolHandler.js');
const protocolMessages = require('./protocolMessages.js');

const userLists = {
    status: {
        anonymous: 'anonymous',
        online: 'online'
    },
    anonymous: lists.createList(),
    online: lists.createList(),
};

const receiveSocket = function (socket) {
    const user = users.createUser({socket, status: userLists.status.anonymous});
    userLists[user.status].add(user, 'id');
    console.log(`Socket ${user.id} connected`);

    return user;
};

const socketHandler = function (socket) {
    const user = receiveSocket(socket);

    user.socket.on('data', function (buffer) {
        const message = protocolMessages.parse(buffer, user);
        protocolMessages.inspect(message);
        protocol.handle(message, userLists);
    });

    user.socket.on('error', function () {
        userLists[user.status].remove(user);
        protocol.UPDATE_FRIENDS(userLists);
        console.log(`Socket ${user.id} disconnected`);
    });

    user.socket.on('end', function () {
        userLists[user.status].remove(user);
        protocol.UPDATE_FRIENDS(userLists);
        console.log(`Socket ${user.id} disconnected`);
    });
};

module.exports = socketHandler;