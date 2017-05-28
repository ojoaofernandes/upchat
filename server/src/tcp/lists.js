'use strict';

const newList = function () {
    const list = [];

    const add = function (user, attr) {
        list[user[attr]] = user;
    };

    const remove = function (user) {
        delete list[user.id];
        delete list[user.username];
    };

    const hasUser = function (user) {
        return list[user.username] ? true : false ;
    };

    const getUser = function (username) {
        return list[username];
    };

    const getUsernames = function () {
        let users = '';

        for (let user in list) {
            users += ' '
            users += list[user].username;
        }

        return users.trim();
    };

    return {
        list,
        add,
        remove,
        getUser,
        hasUser,
        getUsernames
    };
};

const lists = {
    createList() {
        return newList();
    }
};

module.exports = lists;