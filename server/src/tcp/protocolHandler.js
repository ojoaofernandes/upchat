'use strict';

const LOGIN_REQUEST = function (message) {
    if (message.header.params[0] === 'facebook') {
        message.sender.socket.write('url do facebook');
        return;
    }

    message.sender.socket.write('Invalid LOGIN_REQUEST parameter.');
}

const handle = function(message, userLists) {
    if (message.sender.status === userLists.status.anonymous) {
        if (message.header.protocol === 'LOGIN_REQUEST') {
            LOGIN_REQUEST(message);
            return;
        }

        message.sender.socket.write('Invalid message.');
        return;
    }
};

const protocolHandler = {
    handle
};

module.exports = protocolHandler;