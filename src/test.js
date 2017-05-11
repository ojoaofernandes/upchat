'use strict';

var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost:27017/upchat');

var User = mongoose.model('users', { username: String });

var ruben = new User({ username: 'ruben' });
ruben.save(function (err) {
  if (err) {
    console.log(err);
  } else {
    console.log('foi');
  }
});