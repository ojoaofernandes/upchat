'use strict';

var net = require('net');

const LOGIN_REQUEST = function (message, userLists) {
    const sender = message.sender;
    const credentials = {
        username: message.header.params[0].toLowerCase(),
        password: message.header.params[1]
    };

    const user = userLists.online.getUser(credentials.username);

    if (!user) {
        sender.status   = userLists.status.online;
        sender.username = credentials.username;
        sender.password = credentials.password;

        userLists.online.add(sender, 'username');
        userLists.anonymous.remove(sender);

        sender.socket.write(
            ' LOGIN_SUCCESS\r\n\r\n'
            + userLists.online.getUsernames().replace(sender.username, '').trim()
            + '\r\n#\r\n'
        );

        UPDATE_FRIENDS(userLists);
        return;
    }

    if (user && user.password === credentials.password) {
        userLists.online.add(sender, 'username');
        userLists.anonymous.remove(sender);

        sender.socket.write(
            ' LOGIN_SUCCESS\r\n\r\n'
            + userLists.online.getUsernames().replace(sender.username, '').trim()
            + '\r\n#\r\n'
        );

        UPDATE_FRIENDS(userLists);
        return;
    }

    if (user && user.password !== credentials.password) {
         sender.socket.write(' LOGIN_ERROR\r\n\r\n#\r\n');
         return;
    }
};

const MESSAGE_TO = function (message, userLists) {
    const sender = message.sender;
    const receiverUsername = message.header.params[0];
    const receiver = userLists.online.getUser(receiverUsername);

    receiver.socket.write(
        'MESSAGE_FROM '
        + sender.username
        + '\r\n\r\n'
        + message.body
        + '\r\n#\r\n'
    );
};

const UPDATE_FRIENDS = function (userLists) {
    let list = userLists.online.list;

    for (let user in list) {
        list[user].socket.write(
            'UPDATE_FRIENDS \r\n\r\n'
            + userLists.online.getUsernames().replace(list[user].username, '').trim()
            + '\r\n#\r\n'
        );
    }
};

const LOGIN_SUCCESS = function (message) {
    if (message.header.protocol === 'LOGIN_SUCCESS') {
        if(message.header.params[0] === 'facebook'){

            var client = new net.Socket();
            client.connect(9090, '127.0.0.1', function() {
            console.log('Connected');  // acknowledge socket connection
            client.write("LOGIN_SUCCESS" + " " + message.header.params[1] + " " + message.header.params[2] +"\n\r\n\r"); // send info to Server
            });

            client.on('close', function() {
            console.log('Connection closed');
            });


            client.on('end',function(){
            console.log("Reading end");
            });

            client.on('error', function(err){
            console.log("Error: "+err.message);
            })

            console.log("enviou mensagem para : 9090");

        }
        if(message.header.params[0] === 'twitter'){

            var client = new net.Socket();
            client.connect(9090, '127.0.0.1', function() {
            console.log('Connected');  // acknowledge socket connection
            client.write("LOGIN_SUCCESS" + " "  + "twitter" + " "+ message.header.params[1] + " " + message.header.params[2] +"\n\r\n\r"); // send info to Server
            });

            client.on('close', function() {
            console.log('Connection closed');
            });


            client.on('end',function(){
            console.log("Reading end");
            });

            client.on('error', function(err){
            console.log("Error: "+err.message);
            })

            console.log("enviou mensagem para : 9090");

        }
        if(message.header.params[0] === 'google'){

            var client = new net.Socket();
            client.connect(9090, '127.0.0.1', function() {
            console.log('Connected');  // acknowledge socket connection
            client.write("LOGIN_SUCCESS" + " " + "google" + " " + message.header.params[1] + " " +"\n\r\n\r"); // send info to Server
            });

            client.on('close', function() {
            console.log('Connection closed');
            });

            client.on('end',function(){
            console.log("Reading end");
            });

            client.on('error', function(err){
            console.log("Error: "+err.message);
            })

            console.log("enviou mensagem para : 9090");

        }
    }

}

const handle = function(message, userLists) {
    if (message.sender.status === userLists.status.anonymous) {
        if (message.header.protocol === 'LOGIN_REQUEST') {
            LOGIN_REQUEST(message, userLists);
            return;
        }

       else if (message.header.protocol === 'LOGIN_SUCCESS') {
            LOGIN_SUCCESS(message);
            return;
        }

        message.sender.socket.write('Invalid message.');
        return;
    }

    if (message.sender.status === userLists.status.online) {
        if (message.header.protocol === 'MESSAGE_TO') {
            MESSAGE_TO(message, userLists);
            return;
        }
    }
};

const protocolHandler = {
    handle,
    UPDATE_FRIENDS
};

module.exports = protocolHandler;