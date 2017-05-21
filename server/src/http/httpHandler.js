'use strict';

const user = {
    'user': {
        'name': 'Alice',
        'age': '20'
    }
};

const error = {
    'error': 'page not found'
};

const httpHandler = function (req, res) {
    if (req.url === '/auth/callback/facebook') {
        res.writeHead(200, {'Content-Type': 'application/json'});
        res.write(JSON.stringify(user));
    } else {
        res.writeHead(404, {'Content-Type': 'application/json'});
        res.write(JSON.stringify(error));
    }

    res.end();
};

module.exports = httpHandler;