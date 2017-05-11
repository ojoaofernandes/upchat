'use strict';

const net = require('net');

const server = net.createServer(function (connection) {
    connection.write('Connection established with the server...');

    connection.on('data', function (message) {
        console.log(message.toString());
    });
});

server.listen(3000);