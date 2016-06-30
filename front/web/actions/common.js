import fetch from 'isomorphic-fetch';
import queryString from 'query-string';
import {pushPath } from 'redux-simple-router';

export const error_code={
    "101":"API参数错误",
    "2001":"MODEL参数错误",
    "2101":"错误 - 当前夺宝不在进行中",
    "2102":"错误 - 当前夺宝参与数量不足",
    "2103":"错误 - 最新的时时彩还没有开奖",
    "2104":"错误 - 随机抽奖码列表已经生成",
    "2105":"错误 - 当前夺宝不在满人次状态",
    "2106":"错误 - 账户余额不足",
    "2201":"错误 - 晒单长度错误",
};
const base_url = "";

export function post_async(url,params,callback){
    let ajax_url = '';
    if(/^http/.test(url)){
        ajax_url = url
    }else{
        ajax_url = base_url+ url;
    }

    if(params && typeof(params)!=="string"){
        params=queryString.stringify(params);
    }

    return dispatch => {
        return fetch(ajax_url,{
                        method: "POST",
                        credentials: 'include',//跨域访问
                        headers: {
                            "Content-Type": "application/x-www-form-urlencoded"
                        },
                        body: params
                    })
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
}

export function get_async(url,params,callback){
    let ajax_url = '';
    if(/^http/.test(url)){
        ajax_url = url
    }else{
        ajax_url = base_url+ url;
    }
    
    if(params){
        if(typeof(params)!=="string"){
            params=queryString.stringify(params);
        }
        if(/\?/.test(ajax_url)){
            ajax_url = ajax_url +"&"+params;
        }else{
            ajax_url = ajax_url +"?"+params;
        }
    }

    return dispatch => {
        return fetch(ajax_url,{
                        method: "GET",
                        credentials: 'include',//跨域访问
                        headers: {
                            "Content-Type": "application/x-www-form-urlencoded"
                        },
                    })
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

