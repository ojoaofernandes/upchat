'use strict';

const lists = require('./lists.js');
const users = require('./users.js');

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
    console.log(`The socket ${user.id} connected...`);

    user.socket.on('data', function () {
    });

    user.socket.on('end', function () {
        userLists[user.status].remove(user);
        console.log(`The socket ${user.id} disconnected...`);
    });
};

module.exports = socketHandler;