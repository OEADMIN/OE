package com.openexpense.domain;

import com.openexpense.common.UiUtil;
import com.openexpense.dto.OeResult;
import com.openexpense.exception.OeException;
import com.openexpense.exception.OeHostException;
import com.openexpense.model.Company;
import com.openexpense.model.Session;
import com.openexpense.model.User;
import com.openexpense.service.CompanyService;
import com.openexpense.service.SessionService;
import com.openexpense.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
     *@param logincode string 登录码(如:xjouyi@openexpense.com)
     *@param pass string 密码
     *@return OeResult 返回登录信息
     * @see com.openexpense.dto.OeResult
     */
    public OeResult userSignin(String logincode, String pass){
        try {
            if (StringUtils.isEmpty(logincode) || StringUtils.isEmpty(pass)){
                throw new OeHostException(OeHostException.Type.HOST_USER_PASS_NULL);
            }
            String[] loginArray = logincode.split("@");
            if (loginArray.length != 2){
                throw new OeHostException(OeHostException.Type.HOST_CODE_FORMART_ERROR);
            }
            Company company = companyService.getCompanyByDomain(loginArray[1], CompanyService.Type.NORMAL);
            User user = userService.getUser(company,loginArray[0],UserService.Type.NORMAL);
            if (!user.getUser_pass().equals(UiUtil.getPassWord(user.getUser_id(),pass))){
                throw new OeHostException(OeHostException.Type.HOST_PASS_ERROR);
            }
            String sessionid = sessionService.newSession(user);

            return  OeResult.getSuccessResult(new Session(sessionid));
        } catch (OeException e) {
            return e.getResult();
        }
    }

    public OeResult userSignOut(String sessionid){
        sessionService.removeSession(sessionid);
        return OeResult.getSuccessResult(null);
    }
}
