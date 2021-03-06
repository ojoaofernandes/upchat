var express = require('express');
var passport = require('passport');
var StrategyFacebook = require('passport-facebook').Strategy;
var StrategyTwitter = require('passport-twitter').Strategy;
var StrategyGoogle = require('passport-google-oauth2').Strategy;

var net = require('net');
var client = new net.Socket();

passport.use(new StrategyFacebook({
    clientID: '1945723129042657',
    clientSecret: '7669419a08d9efeb323b03f35977187a',
    callbackURL: 'http://localhost:3000/login/facebook/return',
    profileFields: ['id', 'emails', 'name', 'photos', 'hometown','friends']
  },
  function(accessToken, refreshToken, profile, cb) {

    if(profile != null){

        client.connect(5570, '127.0.0.1', function() {
        console.log('Connected facebook login socket');  // acknowledge socket connection
        client.write("LOGIN_SUCCESS" + " " + "facebook" + " " +
         profile._json.first_name + " " + profile._json.last_name + " " + "\r\n\r\n" +
          " " + profile.emails[0].value); // send info to Server
        });

        client.on('close', function() {
        console.log('Connection closed in facebook login');
        });
        client.on('end',function(){
        console.log("Reading end in facebook login");
        });

        client.on('error', function(err){
        console.log("Error facebook socket login: "+err.message);
        });
    }
    else{
        client.connect(5570, '127.0.0.1', function() {
        console.log('Connected');  // acknowledge socket connection
        client.write("LOGIN_ERROR" + " " + "facebook" + " " + "\r\n\r\n"); // send info to Server
        });

        client.on('close', function() {
        console.log('Connection closed in facebook login error socket');
        });
        client.on('end',function(){
        console.log("Reading end in facebook login error socket");
        });

        client.on('error', function(err){
        console.log("Error in facebook login error socket: "+err.message);
        });
    }
    console.log("\n************* token **********************************\n\n");
    console.log(accessToken);
    console.log("\n*************  profile *************************************\n");
    console.log(profile);
    console.log("\n******************************************************\n\n");

    return cb(null, profile);
  }));

passport.serializeUser(function(user, cb) {
  cb(null, user);
});

passport.deserializeUser(function(obj, cb) {
  cb(null, obj);
});

var app = express();

app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');

app.use(require('morgan')('combined'));
app.use(require('cookie-parser')());
app.use(require('body-parser').urlencoded({ extended: true }));
app.use(require('express-session')({ secret: 'keyboard cat', resave: true, saveUninitialized: true }));

app.use(passport.initialize());
app.use(passport.session());

app.get('/',
  function(req, res) {
    res.render('home', { user: req.user });
  });

app.get('/login',
  function(req, res){
    res.render('login');
  });

app.get('/login/facebook',
  passport.authenticate('facebook', {scope: ['user_friends', 'email']}));

app.get('/login/facebook/return', 
  passport.authenticate('facebook', { failureRedirect: '/login' }),
  function(req, res) {
    res.redirect('/');
  });

app.get('/profile',
  require('connect-ensure-login').ensureLoggedIn(),
  function(req, res){
    res.render('profile', { user: req.user });
  });

/*********************************** TWITER LOGIN ***********************************/
passport.use(new StrategyTwitter({
    consumerKey: 'PJnxHFNDKjGQ4Tw4I2Q1RBN3q',
    consumerSecret: 'uIcmi6i7vrqDiT4tQY2XIpAQ3E4XFZU9RmnddR9zervGQ4o1t1',
    callbackURL: 'http://127.0.0.1:3000/login/twitter/return'
  },
  function(token, tokenSecret, profile, cb) {

    if(profile!=null){
          client.connect(5570, '127.0.0.1', function() {
          console.log('Connected');  // acknowledge socket connection
          client.write("LOGIN_SUCCESS" + " " + "twitter" + " " + profile.displayName+ " " + "\r\n\r\n" +
            " " + profile._json.friends_count); // send info to Server
          });
         client.on('close', function() {
          console.log('Connection closed in twitter login socket ');
          });
          client.on('end',function(){
          console.log("Reading end in twitter login socket");
          });

          client.on('error', function(err){
          console.log("Error in twitter login socket: "+err.message);
          })
      } else{
          client.connect(5570, '127.0.0.1', function() {
          console.log('Connected');  // acknowledge socket connection
          client.write("LOGIN_ERROR" + " " + "twitter"); // send info to Server
          });
         client.on('close', function() {
          console.log('Connection closed in twitter login error socket');
          });
          client.on('end',function(){
          console.log("Reading end in twitter login error socket");
          });

          client.on('error', function(err){
          console.log("Error in twitter login error socket: "+err.message);
          });
    }

    console.log("\n\n\n************* token **********************************\n\n");
    console.log(token);
    console.log("\n*************  profile **********************************\n");
    console.log(profile);
    console.log("\n*************  token secret *****************************\n");
    console.log(tokenSecret);
    console.log("\n*******************************************************\n\n\n");
    return cb(null, profile);
  }));

passport.serializeUser(function(user, cb) {
  cb(null, user);
});

passport.deserializeUser(function(obj, cb) {
  cb(null, obj);
});

app.get('/login/twitter',
  passport.authenticate('twitter'));

app.get('/login/twitter/return', 
  passport.authenticate('twitter', { failureRedirect: '/login' }),
  function(req, res) {
    res.redirect('/');
  });


/*************************** google login *********************************/

passport.use(new StrategyGoogle({
    clientID: '562298891086-88n4c1l2h8vvrhbs18dd0ti7p1pq56ar.apps.googleusercontent.com',
    clientSecret: 'kx7q822eSxcEW1e4PQBEkJl6',
    callbackURL: "http://127.0.0.1:3000/auth/google/callback"
  },
  function(accessToken, refreshToken, profile, cb) {

    if(profile != null){
    client.connect(5570, '127.0.0.1', function() {
    console.log('Connected');  // acknowledge socket connection
    client.write("LOGIN_SUCCESS" + " " + "google" + " " + profile.displayName + " " +
      "\r\n\r\n" + " "); // send info to Server
    });

    client.on('close', function() {
    console.log('Connection closed in google login socket');
    });
    client.on('end',function(){
    console.log("Reading end in google login socket");
    });

    client.on('error', function(err){
    console.log("Error in google login socket: "+err.message);
    })

    }else {
     
      client.connect(5570, '127.0.0.1', function() {
      console.log('Connected');  // acknowledge socket connection
      client.write("LOGIN_ERROR" + " " + "google"); // send info to Server
      });

      client.on('close', function() {
      console.log('Connection closed in google login error socket');
      });
      client.on('end',function(){
      console.log("Reading end in google login error socket");
      });

      client.on('error', function(err){
      console.log("Error in google login error socket: "+err.message);
      });

    }
   
     console.log("\n\n\n************* token **********************************\n\n");
    console.log(accessToken);
    console.log("\n*************  profile **********************************\n");
    console.log(profile);
    console.log("\n*************  refreshToken *****************************\n");
    console.log(refreshToken);
    console.log("\n*******************************************************\n\n\n");
      return cb(null, profile);
  }));

passport.serializeUser(function(user, cb) {
  cb(null, user);
});

passport.deserializeUser(function(obj, cb) {
  cb(null, obj);
});

app.get('/auth/google',
  passport.authenticate('google', { scope: ['profile'] }));

app.get('/auth/google/callback', 
  passport.authenticate('google', { failureRedirect: '/login' }),
  function(req, res) {
    // Successful authentication, redirect home.
    res.redirect('/');
  });

app.listen(3000);



