'use strict';

const net = require('net');

const client = net.connect(3000);

client.on('connect', function () {
    client.write('New client connected...');
});

client.on('data', function (message) {
    console.log(message.toString());
});

process.stdin.on('readable', function () {
    let message = process.stdin.read();
    if (!message) return;
    message = message.toString().trim();
    client.write(message);
});