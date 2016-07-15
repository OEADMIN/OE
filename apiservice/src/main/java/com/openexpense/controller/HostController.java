package com.openexpense.controller;

import com.openexpense.domain.HostDomain;
import com.openexpense.domain.UserDomain;
import com.openexpense.dto.OeResult;
import com.openexpense.dto.SignIn;
import com.openexpense.dto.SignUp;
import com.openexpense.exception.OeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    *@param signIn SignIn 登录信息
    *@return OeResult
    */
    @RequestMapping(value = "/signin",method = RequestMethod.GET)
    @ResponseBody
    public OeResult signIn(@Valid SignIn signIn, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return OeResult.getDataVaildResult(bindingResult);
        }else{
            return hostDomain.userSignin(signIn);
        }
    }

    /**用户注销 /signout
     * @param id String sessionid
     * @return "OeResult
     */
    @RequestMapping(value = "/signout/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public OeResult signOut(@PathVariable("id") String id){
        return hostDomain.userSignOut(id);
    }

    /**企业注册 /signup
     * @param signUp SignUp 注册内容
     * @return "OeResult
     */
    @ResponseBody
    @RequestMapping(value = "/signup",method = RequestMethod.PUT)
    public OeResult singUp(@Valid SignUp signUp,BindingResult bindingResult) throws OeException {
        if (bindingResult.hasErrors()){
            return OeResult.getDataVaildResult(bindingResult);
        }else{
            return hostDomain.companySignUp(signUp);
        }
    }

}
