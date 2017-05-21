'use strict';

const http = require('http');
const httpHandler = require('./httpHandler.js');

const httpServer = http.createServer(httpHandler);

const server = {
    run(options) {
        const callback = function () {
            console.log('HTTP Server is up and listening on'
                , options.host + ':' + options.port
                , '\nCTRL+C to stop'
            );
        };

        httpServer.listen(options, callback);
    }
};

module.exports = server;