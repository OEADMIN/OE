var request = require('request');
var host_connection = "http://api.jayie.cn";

module.exports.get = function(url,params,callback){
	request.get({
		url:host_connection+url,
	},function(error, response, body){
		callback(body);
	});
}

module.exports.post = function(url,params,callback){
	request.post({
		url:host_connection+url,
	},function(error, response, body){
		callback(response);
	});
}
module.exports.put = function(url,params,callback){
	request.put({
		url:host_connection+url,
	},function(error, response, body){
		callback(response);
	});
}
module.exports.delete = function(url,params,callback){
	request.delete({
		url:host_connection+this.url,
	},function(error, response, body){
		callback(response);
	});
}