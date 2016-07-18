import fetch from 'isomorphic-fetch';
import queryString from 'query-string';
const base_url = "";

function async(type,url,params,callback){
    let 
        ajax_url = base_url+ url,
        option = {
            method: type,
            credentials: 'include',//跨域访问
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            }
        };
    params=queryString.stringify(params);
    if(type=="POST"){
        option.body = params;
    }else if(type == "GET" || type == "PUT"){
        ajax_url = ajax_url +"?"+params;
    }else if(type == "DELETE"){
        option.body = params;
    }

    fetch(ajax_url,option)
        .then(response => response.json())
        .then(function(json){
            callback(json)
        })
        .catch(function(ex) {
            callback({
                s:0,
                m:ex.toString()
            })
        })
}
export let Ajax = {
    get:function(url,params,callback){
        async("GET",url,params,callback);
    },
    post:function(url,params,callback){
        async("POST",url,params,callback);
    },
    delete:function(url,params,callback){
        async("DELETE",url,params,callback);
    },
    put:function(url,params,callback){
        async("PUT",url,params,callback);
    }
}



export function getTimeByDate(data,cData){
    if(data == "0000-00-00 00:00:00" && cData){
        data = cData;
    }
    if(!data){
        return null;
    }
    let now = new Date().getTime();
    let time = new Date(Date.parse(data.replace(/-/g,"/"))).getTime();
    let minutes = now - time;
    
    //计算出相差天数  
    let days = Math.floor(minutes/(24*3600*1000))     
    //计算出小时数       
    let leave1 = minutes%(24*3600*1000)    //计算天数后剩余的毫秒数  
    let hours = Math.floor(leave1/(3600*1000))  
    //计算相差分钟数
    let leave2 = leave1%(3600*1000)        //计算小时数后剩余的毫秒数  
    let mins = Math.floor(leave2/(60*1000))  
    var leave3=leave2%(60*1000)      //计算分钟数后剩余的毫秒数  
    var seconds=Math.round(leave3/1000) 

    hours = hours >0 ? hours+"小时前" : false;
    mins  = mins >0 ? mins+"分钟前" : false;
    
    if(days>7){
        days = data.split(' ')[0];
    }else{
        days  = days >0 ? days+"天前" : false;
    }
    return days|| hours || mins || "刚刚";
}

