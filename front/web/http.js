var request = require('request');
var host_connection = "http://api.jayie.cn";

function async (type,url,params,callback){
	var option = {
		url:host_connection+url,
		method:type,
		formData:params,
		callback :function(error,response,body){
			callback(body);
		}
	}
	request(option);
}
module.exports.get = function(url,params,callback){
	async("GET",url,params,callback);
	// request.get({
	// 	url:host_connection+url,
	// },function(error, response, body){
	// 	callback(body);
	// });
}

module.exports.post = function(url,params,callback){
	async("POST",url,params,callback);
	// request.post({
	// 	url:host_connection+url,
	// },function(error, response, body){
	// 	callback(response);
	// });
}
module.exports.put = function(url,params,callback){
	async("PUT",url,params,callback);
	// request.put({
	// 	url:host_connection+url,
	// },function(error, response, body){
	// 	callback(response);
	// });
}
module.exports.delete = function(url,params,callback){
	async("DELETE",url,params,callback);
	// request.delete({
	// 	url:host_connection+this.url,
	// },function(error, response, body){
	// 	callback(response);
	// });
}