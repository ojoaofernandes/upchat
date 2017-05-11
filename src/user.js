'use strict';

function User(username, socket) {
    this.username = username;
    this.socket = socket;
}

User.prototype.prefix = function() {
    return this.username + '> ';
};

User.prototype.sendMessage = function(msg, from) {
    this.socket.write(from.prefix() + msg);
};

module.exports = User;