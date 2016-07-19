var request = require('request');
var host_connection = "https://api.jayie.cn";

function async (type,url,params,callback){
	var option = {
		url:host_connection+url,
		method:type,
		formData:params,
		oauth:params,
		json:true,
		callback :function(error,response,body){
			var obj = {success:"fail",code:"",data:[{message:"404 not found"}]}
			if(response.statusCode==404){
				callback(obj);
			}else if(response.statusCode>500){
				callback(body);
			}else if(response.statusCode>=200 || response.statusCode<300){
				callback(body);
			}else{
				callback(obj);
			}
		}
	}
	request(option);
}
module.exports.get = function(url,params,callback){
	async("GET",url,params,callback);
}

module.exports.post = function(url,params,callback){
	async("POST",url,params,callback);
}
module.exports.put = function(url,params,callback){
	async("PUT",url,params,callback);
}
module.exports.delete = function(url,params,callback){
	async("DELETE",url,params,callback);
}