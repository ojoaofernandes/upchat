'use script';

const tcpServer = require('./src/tcp/server.js');

tcpServer.run({
    host: 'localhost',
    port: 5511
});