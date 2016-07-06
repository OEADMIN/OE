var webpack = require('webpack')
var webpackDevMiddleware = require('webpack-dev-middleware');
var webpackHotMiddleware = require('webpack-hot-middleware');
var config = require('./webpack.config')

var express = require('express')
var proxy = require('express-http-proxy');
var router = express.Router();
/**
 * server base-on express
 */
var app = new express()
var port = 9999

/**
 * dev middleware,hot middleware
 */
var compiler = webpack(config)
app.use(webpackDevMiddleware(compiler, { noInfo: true, publicPath: config.output.publicPath }))
app.use(webpackHotMiddleware(compiler))

/**
 * static path
 */
app.use("/resource",express.static(__dirname + '/resource'));
app.use("/bower_components",express.static(__dirname + '/bower_components'));
app.use("/static",express.static(__dirname + '/static'));
app.use("/",express.static(__dirname + '/html'));

//setting html engine 
// app.engine('html', require('ejs').renderFile);
// app.set('view engine', 'html');
// app.set('views',__dirname+"/html")


/**
 * support spa
 */
app.use("/(:languages)", function(req, res) {
    res.sendFile(__dirname + '/index.html')
})
/**
 * html route
 */
// router.route(/api\/*\/*/)
// .all(function(req, res, next) {
//   // runs for all HTTP verbs first
//   // think of it as route specific middleware!
//   next();
// })
// .get(function(req, res, next) {
//   res.json(req.user);
// })
// .put(function(req, res, next) {
//   // just an example of maybe updating the user
//   req.user.name = req.params.name;
//   // save user ... etc
//   res.json(req.user);
// })
// .post(function(req, res, next) {
// 	console.log(req);
//   next(new Error('not implemented'));
// })
// .delete(function(req, res, next) {
//   next(new Error('not implemented'));
// })
app.post("/api/getuser",function(req,res){
	res.send({success:1})
})
/**
 * start server
 */
app.listen(port, function(error) {
    if (error) {
        console.error(error)
    } else {
        console.info("==> express Listening on port %s. Open up http://localhost:%s/ in your browser.", port, port)
    }
})
