var webpack = require('webpack')
var webpackDevMiddleware = require('webpack-dev-middleware');
var webpackHotMiddleware = require('webpack-hot-middleware');
var config = require('./webpack.config')
var httpRequest = require('./http');

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
app.use(/\/zh|\/en/, function(req, res) {
    res.sendFile(__dirname + '/index.html')
})

app.get(/^\/api(\/[a-zA-Z+])+/,function(req,res){
    var url = req._parsedOriginalUrl.pathname;
    url = url.substring(4,url.length)
    httpRequest.get(url,req.query,function(data){
        res.send(data);
    });
})
app.post(/^\/api(\/[a-zA-Z+])+/,function(req,res){
    res.send({success:1});
})
app.delete(/^\/api(\/[a-zA-Z+])+/,function(req,res){
    res.send({success:1});
})
app.put(/^\/api(\/[a-zA-Z+])+/,function(req,res){
    res.send({success:1});
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
