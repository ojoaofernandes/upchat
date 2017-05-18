'use strict';

const handle = function(message) {
    if (message.sender.status === 'anonymous') {
        if (message.header.protocol === 'LOGIN_REQUEST') {
            if (message.header.params[0] === 'facebook') {
                message.sender.socket.write('url do facebook');
                return;
            }
            if (message.header.params[0] === 'twitter') {
                message.sender.socket.write('url do twitter');
                return;
            }

            message.sender.socket.write('Invalid LOGIN_REQUEST parameter.');
        }

        message.sender.socket.write('Invalid message for an anonymous client.');
        return;
    }
};

const protocolHandler = {
    handle
};

module.exports = protocolHandler;