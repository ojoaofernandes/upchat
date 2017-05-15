'use strict';

const net = require('net');
const User = require('./user.js');

const clients = [];

const broadcast = function(from, message) {
    users.forEach(function (user) {
        if (user.connection === from.connection) return;
        user.sendMessage(from, message);
    });
};

const unicast = function (from, to, message) {
    let receiver = users.filter(function (user) {
        if (user.connection !== from.connection && user.username === to) {
            return user;
        }
    }).shift();

    receiver.sendMessage(from, message);
};

const changeUsername = function (from, newUsername) {
    let user = users.filter(function (user) {
        if (user.connection == from.connection) {
            return user;
        }
    }).shift();

    user.username = newUsername;
};

const server = net.createServer(function (connection) {
    let user = new User('anonymous', connection);

    users.push(user);
    console.log('New client connected...');

    user.connection.on('data', function (message) {
        message = message.toString();

        let firstSpaceIndex = message.indexOf(' ');
        let messageType = message.substr(0, firstSpaceIndex);
        let messageBody = message.substr(++firstSpaceIndex);

        switch (messageType) {
            case 'CHANGE_USERNAME': {
                changeUsername(user, messageBody);
            }; break;
            case 'BROADCAST': {
                broadcast(user, messageBody);
            }; break;
            case 'MESSAGE_TO': {
                firstSpaceIndex = messageBody.indexOf(' ');
                let to = messageBody.substr(0, firstSpaceIndex);
                let msg = messageBody.substr(++firstSpaceIndex);
                unicast(user, to, msg);
            }; break;
            default: console.log(message); break;
        }
    });

    user.connection.on('end', function () {
        console.log('A client has disconnected...');
    });
});

server.listen(3000);
console.log('Server up listen on port 3000...');