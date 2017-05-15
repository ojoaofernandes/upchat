'use strict';

const net = require('net');
const socketHandler = require('./socketHandler.js');

const netServer = net.createServer(socketHandler);

const server = {
    run(options) {
        const callback = function () {
            console.log('TCP Server is up and listening on'
                , options.host + ':' + options.port
                , '\nCTRL+C to stop'
            );
        };

        netServer.listen(options, callback);
    }
};

module.exports = server;