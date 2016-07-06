package com.openexpense.controller;

import com.openexpense.domain.HostDomain;
import com.openexpense.domain.UserDomain;
import com.openexpense.dto.OeResult;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserDomain  userDomain;

    @Autowired
    HostDomain hostDomain;

    /**用户登录 /signin/{logincode}/{pass}
    *@param logincode string 用户id
    *@param pass string 密码
    *@return id + pass
    */
    @ResponseBody
    @RequestMapping(value = "/signin/{logincode}/{pass}",method = RequestMethod.GET)
    public OeResult signIn(@PathVariable("logincode")String logincode, @PathVariable("pass")String pass){
        return hostDomain.userLogin(logincode,pass);
    }

    /**用户注销 /signout
    *@return "hello"
    */
    @ResponseBody
    @RequestMapping(value = "/signout",method = RequestMethod.GET)
    public String signOut(){
        return "hello";
    }
}
