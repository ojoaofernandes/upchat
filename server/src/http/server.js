'use strict';

const http = require('http');
const httpHandler = require('./httpHandler.js');


// import our modules 
var Router       = require('router')
var finalhandler = require('finalhandler')
var compression  = require('compression')
var bodyParser   = require('body-parser')
var passport = require('passport-facebook')
var passportStrategy = require('passport-strategy')


/*
const httpServer = http.createServer(httpHandler);

const server = {
    run(options) {
        const callback = function () {
            console.log('HTTP Server is up and listening on'
                , options.host + ':' + options.port
                , '\nCTRL+C to stop'
            );
        };

        httpServer.listen(options, callback);
    }
};

module.exports = server;*/


 
// store our message to display 
var message = "Hello World!"
 
// initialize the router & server and add a final callback. 
var router = Router()
var server = http.createServer(function onRequest(req, res) {
  router(req, res, finalhandler(req, res))
})
 
// use some middleware and compress all outgoing responses 
router.use(compression())
 
// handle `GET` requests to `/message` 
router.get('/message', function (req, res) {
  res.statusCode = 200
  res.setHeader('Content-Type', 'text/plain; charset=utf-8')
  res.end(message + '\n')
})

// Redirect the user to Facebook for authentication.  When complete,
// Facebook will redirect the user back to the application at
//     /auth/facebook/callback
router.get('/auth/facebook', passport.authenticate('facebook'));

// Facebook will redirect the user to this URL after approval.  Finish the
// authentication process by attempting to obtain an access token.  If
// access was granted, the user will be logged in.  Otherwise,
// authentication has failed.
router.get('/auth/facebook/callback',
  passport.authenticate('facebook', { successRedirect: '/',
                                      failureRedirect: '/login' }));
 


// create and mount a new router for our API 
var api = Router()
router.use('/api/', api)
 
// add a body parsing middleware to our API 
api.use(bodyParser.json())
 
// handle `PATCH` requests to `/api/set-message` 
api.patch('/set-message', function (req, res) {
  if (req.body.value) {
    message = req.body.value
 
    res.statusCode = 200
    res.setHeader('Content-Type', 'text/plain; charset=utf-8')
    res.end(message + '\n')
  } else {
    res.statusCode = 400
    res.setHeader('Content-Type', 'text/plain; charset=utf-8')
    res.end('Invalid API Syntax\n')
  }
})
 
// make our http server listen to connections 
server.listen(8080)