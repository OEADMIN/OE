var request = require('request');

function HttpRequest(url,data,callback){
	callback      = typeof data === "object"?data:callback;
	this.callback = callback || function;
	this.url      = url;
	this.data     = data;
}

HttpRequest.prototype.get = function(){
	var _this = this;
	request.get({
		url:"http://114.215.80.196:8080/openexpense/"+this.url,
	},function(error, response, body){
		this.callback(response);
	});
}

HttpRequest.prototype.post = function(){
	var _this = this;
	request.post({
		url:"http://114.215.80.196:8080/openexpense/"+this.url,
	},function(error, response, body){
		this.callback(response);
	});
}
HttpRequest.prototype.put = function(){
	var _this = this;
	request.put({
		url:"http://114.215.80.196:8080/openexpense/"+this.url,
	},function(error, response, body){
		this.callback(response);
	});
}
HttpRequest.prototype.delete = function(){
	var _this = this;
	request.put({
		url:"http://114.215.80.196:8080/openexpense/"+this.url,
	},function(error, response, body){
		this.callback(response);
	});
}