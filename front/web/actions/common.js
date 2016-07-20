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


function check(type,key){
    var 
        typeString = "",
        reg;
    switch(type){
        case "email":
            typeString = '^([a-zA-Z0-9_\\-\\.])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$';
            break;
        case 'mobile':
            typeString = '^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$';
            break;
        case 'tel':
            typeString = '^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$';
            break;
        case 'date':
            //格式yyyy-mm-dd
            typeString = '^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$';
            break;
        case 'QQ':
            typeString = '^[1-9][0-9]{4,}$';
            break;
        case 'digital':
            typeString ='^\\d\\d*$';
            break;
    }
    reg = new RegExp(typeString);
    return reg.test(key);
}

export let Verificate = {
    email:function(key){
        return check("email",key);
    },
    mobile:function(key){
        return check("mobile",key);
    },
    tel:function(key){
        return check("tel",key);
    },
    date:function(key){
        return check("date",key);
    },
    QQ:function(key){
        return check("QQ",key);
    },
    digital:function(key){
        return check("digital",key);
    }
} 

