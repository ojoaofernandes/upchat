'use strict';

const protocolHandler = {
    handle(message) {
        console.log(message.toString());
    },
};

module.exports = protocolHandler;