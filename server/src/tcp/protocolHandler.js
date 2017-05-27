'use strict';

var net = require('net');

const LOGIN_REQUEST = function (message) {
    if (message.header.params[0] === 'facebook') {
        message.sender.socket.write('url do facebook');
        return;
    }

    message.sender.socket.write('Invalid LOGIN_REQUEST parameter.');
}

const LOGIN_SUCCESS = function (message) {
    if (message.header.protocol === 'LOGIN_SUCCESS') {
    
    var client = new net.Socket();
    client.connect(9090, '127.0.0.1', function() {
    console.log('Connected');  // acknowledge socket connection
    client.write(message.header.params[0] + "experiencia"); // send info to Server
    });

    client.on('close', function() {
    console.log('Connection closed');
    });
    }

    client.on('end',function(){
    console.log("Reading end");
    });

    client.on('error', function(err){
    console.log("Error: "+err.message);
    })

    console.log("enviou mensagem para : 9090");

    message.sender.socket.write('cenas e coisas');
}

const handle = function(message, userLists) {
    if (message.sender.status === userLists.status.anonymous) {
        if (message.header.protocol === 'LOGIN_REQUEST') {
            LOGIN_REQUEST(message);
            return;
        }

       else if (message.header.protocol === 'LOGIN_SUCCESS') {
            LOGIN_SUCCESS(message);
            return;
        }


        message.sender.socket.write('Invalid message.');
        return;
    }
};

const protocolHandler = {
    handle
};

module.exports = protocolHandler;