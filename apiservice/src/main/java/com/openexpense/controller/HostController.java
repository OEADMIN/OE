package com.openexpense.controller;

import com.openexpense.domain.HostDomain;
import com.openexpense.domain.UserDomain;
import com.openexpense.dto.OeResult;
import com.openexpense.dto.SignIn;
import com.openexpense.dto.SignUp;
import com.openexpense.exception.OeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**用户登录接口
 *2016/06/30.
 *@author xjouyi@163.com
 *@version 0.1
 */
@RestController
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
    @RequestMapping(value = "/signin",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public OeResult signIn(@Valid SignIn signIn, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return OeResult.getDataVaildResult(bindingResult);
        }else{
            try {
                return OeResult.getSuccessResult(hostDomain.userSignin(signIn));
            } catch (OeException e) {
                e.printStackTrace();
                return e.getResult();
            }
        }
    }

    /**用户注销 /signout
     * @param id String sessionid
     * @return "OeResult
     */
    @RequestMapping(value = "/signout/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public OeResult signOut(@PathVariable("id") String id){
        hostDomain.userSignOut(id);
        return OeResult.getSuccessResult(null);
    }

    /**企业注册 /signup
     * @param signUp SignUp 注册内容
     * @return "OeResult
     */
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public OeResult singUp(@Valid SignUp signUp,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return OeResult.getDataVaildResult(bindingResult);
        }else{
            try {
                return OeResult.getSuccessResult(hostDomain.companySignUp(signUp));
            } catch (OeException e) {
                e.printStackTrace();
                return e.getResult();
            }
        }
    }

}
