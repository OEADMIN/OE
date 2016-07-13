export default class ApiService{
	constructor(){
		this.remoteUrl = "https://api.jayie.cn/";
	}
	getData(config,call){
		let url = this.remoteUrl + config.action;
		for(param of config.params)	{
			url = url + "/" + param
		}
		
	    fetch(url)
        .then(response=>response.json())
        .then(json=>call(json))
	}
}
