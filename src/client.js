'use strict';

const net = require('net');

const client = net.connect(3000);

client.on('connect', function () {
    client.write('New client connected...');
});

client.on('data', function (message) {
    console.log(message.toString());
});