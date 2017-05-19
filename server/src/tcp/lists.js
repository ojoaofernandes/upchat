'use strict';

const newList = function () {
    const list = [];

    const add = function (item) {
        list[item.id] = item;
    };

    const remove = function (item) {
        delete list[item.id];
    };

    return {
        list,
        add,
        remove
    };
};

const lists = {
    createList() {
        return newList();
    }
};

module.exports = lists;