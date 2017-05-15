'use strict';

const generateId = function (socket) {
    return `${socket.remoteAddress}:${socket.remotePort}`;
};

const newUser = function ({socket, status}) {
    return {
        id: generateId(socket),
        status,
        socket
    };
};

const users = {
    createUser({socket, status}) {
        return newUser(arguments[0]);
    }
};

module.exports = users;