'use strict';

const net = require('net');
const User = require('./user.js');

const users = [];

const server = net.createServer(function (connection) {
    let user = new User('anonymous', connection);

    users.push(user);
    console.log('New client connected...');

    connection.on('data', function (message) {
    });

    connection.on('end', function () {
        console.log('A client has disconnected...');
    });
});

server.listen(3000);
console.log('Server up listen on port 3000...');