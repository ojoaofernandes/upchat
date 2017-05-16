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

const socketHandler = function (socket) {
    const user = users.createUser({socket, status: userLists.status.anonymous});
    userLists[user.status].register(user);
    console.log(`Socket ${user.id} connected`);

    user.socket.on('data', function (buffer) {
        const message = protocolMessages.parse(buffer, user);
        protocolMessages.inspect(message);
        protocol.handle(message);
    });

    user.socket.on('end', function () {
        userLists[user.status].remove(user);
        console.log(`Socket ${user.id} disconnected`);
    });
};

module.exports = socketHandler;