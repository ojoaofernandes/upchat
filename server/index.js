'use script';

const tcpServer  = require('./src/tcp/server.js');
//const httpServer = require('./src/http/server.js');

tcpServer.run({
    host: 'localhost',
    port: 5570
});

/*httpServer.run({
    host: 'localhost',
    port: 5580
});*/