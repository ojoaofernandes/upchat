'use strict';

const net = require('net');

const connections = [];

const broadcast = function (sender, message) {
    connections.forEach(function (connection) {
        if (sender === connection) return;
        connection.write(message);
    });
};

const server = net.createServer(function (connection) {
    console.log('New client connected...');
    connections.push(connection);

    connection.on('data', function (message) {
        broadcast(connection, message);
    });
});

server.listen(3000);
console.log('Server up listen on port 3000...');