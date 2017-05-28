'use script';

const tcpServer  = require('./src/tcp/server.js');

const database = require('./src/tcp/database.js');
database.restore();

tcpServer.run({
    host: '192.168.108.180',
    port: 5570
});