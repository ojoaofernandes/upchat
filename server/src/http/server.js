var express = require('express');
var passport = require('passport');
var StrategyFacebook = require('passport-facebook').Strategy;
var StrategyTwitter = require('passport-twitter').Strategy;
var StrategyGoogle = require('passport-google-oauth2').Strategy;

passport.use(new StrategyFacebook({
    clientID: '1945723129042657',
    clientSecret: '7669419a08d9efeb323b03f35977187a',
    callbackURL: 'http://localhost:3000/login/facebook/return'
  },
  function(accessToken, refreshToken, profile, cb) {
    console.log("\n************* token **********************************\n\n");
    console.log(accessToken);
    console.log("\n*************  profile *************************************\n");
    console.log(profile);
    console.log("\n*************  name *************************************\n");
    console.log(profile.displayName);
    console.log("\n*************  isd *************************************\n");
    console.log(profile.id);
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
  passport.authenticate('facebook'));

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
    console.log("\n\n\n************* token **********************************\n\n");
    console.log(token);
    console.log("\n*************  profile **********************************\n");
    console.log(profile);
    console.log("\n*************  token secret *****************************\n");
    console.log(tokenSecret);
    console.log("\n*************  name *************************************\n");
    console.log(profile.displayName);
 	console.log("\n*************  friends count ******************************\n");
    console.log(profile._json.friends_count);
    console.log("\n*************  description *****************************\n");
    console.log(profile._json.description);
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
    
      return cb(null, profile);
    }
));

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

