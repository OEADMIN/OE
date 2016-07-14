package com.openexpense.controller;

import com.openexpense.domain.HostDomain;
import com.openexpense.domain.UserDomain;
import com.openexpense.dto.OeResult;
import com.openexpense.dto.SignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    *@return OeResult
    */
    @RequestMapping(value = "/signin",method = RequestMethod.GET)
    public OeResult signIn(@RequestParam("logincode")String logincode, @RequestParam("pass")String pass){
        return hostDomain.userSignin(logincode,pass);
    }

    /**用户注销 /signout
     * @param id String sessionid
     * @return "OeResult
     */
    @RequestMapping(value = "/signout/{id}",method = RequestMethod.DELETE)
    public OeResult signOut(@PathVariable("id") String id){
        return hostDomain.userSignOut(id);
    }

    /**企业注册 /signup
     * @param signUp SignUp 注册内容
     * @return "OeResult
     */
    @ResponseBody
    @RequestMapping(value = "/signup",method = RequestMethod.PUT)
    public OeResult singUp(@Valid SignUp signUp,BindingResult bindingResult){
        return hostDomain.companySignUp(signUp);
    }

}
