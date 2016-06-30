package com.openexpense.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**用户登录接口
 *2016/06/30.
 *@author xjouyi@163.com
 *@version 0.1
 */
@Controller
@RequestMapping("/host")
public class HostController {

    /**用户登录
    *@param id string 用户id
    *@param pass string 密码
    *@return id + pass
    */
    @ResponseBody
    @RequestMapping(value = "/signin/{id}/{pass}",method = RequestMethod.GET)
    public String signIn(@PathVariable("id")String id,@PathVariable("pass")String pass){
        return id+","+pass;
    }

    /**用户注销
    *@return "hello"
    */
    @ResponseBody
    @RequestMapping(value = "/signout",method = RequestMethod.GET)
    public String signOut(){
        return "hello";
    }
}
