package com.openexpense.service.impl;

import com.openexpense.common.UiUtil;
import com.openexpense.dao.UserDao;
import com.openexpense.dto.SignUp;
import com.openexpense.exception.OeException;
import com.openexpense.exception.OeExceptionType;
import com.openexpense.model.Company;
import com.openexpense.model.User;
import com.openexpense.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;


/**用户服务实现
 *2016/07/06.
 *@author xjouyi@163.com
 *@version 0.1
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    /** @see com.openexpense.service.UserService#getUser(Company, String)  */
    @Override
    public User getUser(Company company, String usercode) throws OeException {
        if (company == null){
            throw new OeException(OeExceptionType.USER_COMPANY_NOT_EMPTY);
        }
        if(StringUtils.isEmpty(usercode)){
            throw new OeException("usercode",OeExceptionType.NOT_EMPTY);
        }
        return userDao.queryOneCompanyUser(company.getCompany_id(),usercode);
    }

    @Override
    public User addUser(Company company, SignUp signUp) throws OeException {
        if(this.getUser(company,signUp.getUcode()) != null){
            throw new OeException(OeExceptionType.USER_CODE_EXISTS);
        }
        String id = UUID.randomUUID().toString();
        User user = new User();
        user.setUser_id(id);
        user.setUser_code(signUp.getUcode());
        user.setUser_name(signUp.getUname());
        user.setUser_pass(UiUtil.getPassWord(id,signUp.getUpass()));
        user.setUser_email(signUp.getUemail());
        user.setUser_state(Type.NORMAL.getName());
        user.setIs_founder(true);
        user.setCompany_id(company.getCompany_id());
        user.setCreate_date(new Date(System.currentTimeMillis()));
        userDao.insert(user);
        return user;
    }
}
