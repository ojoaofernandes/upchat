'use strict';

function User(username, connection) {
    this.username = username;
    this.connection = connection;
}

User.prototype.prefix = function() {
    return this.username + '> ';
};

User.prototype.sendMessage = function(from, message) {
    this.connection.write(from.prefix() + message);
};

module.exports = User;