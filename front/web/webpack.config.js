var path = require('path')
var webpack = require('webpack')

var devFlagPlugin = new webpack.DefinePlugin({
  //__DEVTOOLS: JSON.stringify(JSON.parse(process.env.DEBUG || 'false'))
  __DEVTOOLS: false
});

var _IS_PRODUCTION = "test";

var _IS_HOT_MIDD = ["./index"];
var _PublicPath = "/static/"; 

//_PublicPath根据不同的环境打包不同的路径
//_IS_HOT_MIDD 线上不需要自动刷新功能
if(_IS_PRODUCTION == "test"){
  _IS_HOT_MIDD = ["./index", 'webpack-hot-middleware/client'];
}

module.exports = {
    //devtool: 'cheap-source-map',
    devtool: 'cheap-module-source-map',
    entry:{
        vendors: ['react', 'react-dom','react-redux', 'react-router', 'history', 'redux-simple-router','lodash','query-string'],
        index:_IS_HOT_MIDD,
    },
  output: {
    path: path.join(__dirname, 'static'),
    filename: 'bundle.js',
    publicPath: _PublicPath,
  },
  plugins: [
      new webpack.optimize.OccurenceOrderPlugin(),
      new webpack.optimize.CommonsChunkPlugin('vendors', 'vendors.js'),
      new webpack.HotModuleReplacementPlugin(),
      new webpack.NoErrorsPlugin(),
      devFlagPlugin
  ],
  module: {
    loaders: [
    //js
    {
      test: /\.js$/,
      loaders: [ 'babel' ],
      exclude: /node_modules/,
      include: __dirname
    },
    //css
    {
      test: /\.css?$/,
      loaders: [ 'style', 'raw' ],
      include: __dirname
    }
    // LESS
    ,{
      test: /\.less$/,
      loaders: [ 'style', 'css','less' ],
      include: __dirname
    },
    {
        test: /\.(eot|woff|woff2|ttf|svg|png|jpg|gif)$/,
        exclude: /node_modules/,
        loader: 'url-loader?limit=30000&name=resource/images/[name]-[hash].[ext]',
        include: __dirname
    }
    ]
  }
}


// When inside Redux repo, prefer src to compiled version.
// You can safely delete these lines in your project.
var reduxSrc = path.join(__dirname, '..', '..', 'src')
var reduxNodeModules = path.join(__dirname, '..', '..', 'node_modules')
var fs = require('fs')
if (fs.existsSync(reduxSrc) && fs.existsSync(reduxNodeModules)) {
  // Resolve Redux to source
  module.exports.resolve = { alias: { 'redux': reduxSrc } }
  // Compile Redux from source
  module.exports.module.loaders.push({
    test: /\.js$/,
    loaders: [ 'babel' ],
    include: reduxSrc
  })
}
