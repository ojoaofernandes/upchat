'use strict';

const fs = require('fs');
const path = './src/tcp/users.json';

const database = {
    users: []
};

database.restore = function () {
    let json = fs.readFileSync(path);
    json = JSON.parse(json);

    for (let i = 0; i < json.users.length; i++) {
        database.users[json.users[i].username] = json.users[i].password;
    }
};

database.showAll = function () {
    for (let user in database.users) {
        console.log(user + ': '+ database.users[user]);
    };
};

const updateFile = function () {
    let json = {
        users: []
    };

    for (let user in database.users) {
        json.users.push({
            username: user,
            password: database.users[user]
        });
    }

    fs.writeFileSync(path, JSON.stringify(json), 'utf-8');
};

database.getUser = function (username) {
    return database.users[username];
};

database.addUser = function (username, password) {
    database.users[username] = password;
    updateFile();
};

module.exports = database;