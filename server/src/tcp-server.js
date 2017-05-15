'use strict';

const net = require('net');

const server = net.createServer(function (socket) {
    socket.end('goodbye\n');
});

const serverRunner = {
    run: function (options) {
        const callback = function () {
            console.log('TCP Server is up and listening on'
                , options.host + ':' + options.port
                , '\nCTRL+C to stop'
            );
        };

        server.listen(options, callback);
    }
};

module.exports = serverRunner;