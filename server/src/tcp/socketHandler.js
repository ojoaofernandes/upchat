'use strict';

const socketHandler = function (socket) {
    socket.end('server> hey.');
};

module.exports = socketHandler;