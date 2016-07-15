package com.openexpense.domain;

import com.openexpense.common.UiUtil;
import com.openexpense.dto.OeResult;
import com.openexpense.dto.SignIn;
import com.openexpense.dto.SignUp;
import com.openexpense.exception.OeException;
import com.openexpense.exception.OeExceptionType;
import com.openexpense.model.Company;
import com.openexpense.model.Session;
import com.openexpense.model.User;
import com.openexpense.service.CompanyService;
import com.openexpense.service.SessionService;
import com.openexpense.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**服务业务领域
 *2016/07/06.
 *@author xjouyi@163.com
 */
@Service
public class HostDomain {

    /**企业对象服务*/
    @Autowired
    CompanyService companyService;

    /**用户对象服务*/
    @Autowired
    UserService userService;

    /**Session对象服务*/
    @Autowired
    SessionService sessionService;

    /**根据企业域获取企业对象
     *@param signIn string 登录码(如:xjouyi@openexpense.com)
     *@return OeResult 返回登录信息
     * @see com.openexpense.dto.OeResult
     */
    public OeResult userSignin(SignIn signIn){
        try {
            String[] loginArray = signIn.getLogincode().split("@");
            Company company = companyService.getCompanyByDomain(loginArray[1]);
            User user = userService.getUser(company,loginArray[0]);

            if (!company.getCompany_state().equals(CompanyService.Type.NORMAL.getName())){
                throw new OeException(OeExceptionType.COMPANY_NOT_ACTIVE);
            }
            if (!user.getUser_state().equals(UserService.Type.NORMAL.getName())){
                throw new OeException(OeExceptionType.USER_NOT_ACTIVE);
            }
            if (!user.getUser_pass().equals(UiUtil.getPassWord(user.getUser_id(),signIn.getPass()))){
                throw new OeException(OeExceptionType.HOST_PASS_ERROR);
            }

            String sessionid = sessionService.newSession(user);
            return  OeResult.getSuccessResult(new Session(sessionid));
        } catch (OeException e) {
            return e.getResult();
        }
    }

    /**用户注销
     * @param sessionid string 用户sessionid
     * @return  OeResult 返回注销是否成功
     * @see com.openexpense.dto.OeResult
     */
    public OeResult userSignOut(String sessionid){
        sessionService.removeSession(sessionid);
        return OeResult.getSuccessResult(null);
    }



    /**
     * 企业注册并登录
     * @param signUp SignUp 企业注册信息
     * @return
     */
    public OeResult companySignUp(SignUp signUp){
        return null;
    }
}
