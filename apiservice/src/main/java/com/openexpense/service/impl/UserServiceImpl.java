package com.openexpense.service.impl;

import com.openexpense.dao.UserDao;
import com.openexpense.exception.OeUserException;
import com.openexpense.model.Company;
import com.openexpense.model.User;
import com.openexpense.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**用户服务实现
 *2016/07/06.
 *@author xjouyi@163.com
 *@version 0.1
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    /** @see com.openexpense.service.UserService#getUser(Company, String, Type)  */
    @Override
    public User getUser(Company company, String usercode,UserService.Type type) throws OeUserException {
        if (company == null){
            throw new OeUserException(OeUserException.Type.USER_COMPANY_NULL);
        }
        if(StringUtils.isEmpty(usercode)){
            throw new OeUserException(OeUserException.Type.USER_CODE_NULL);
        }
        User user = userDao.queryOneCompanyUser(company.getCompany_id(),usercode);
        if (user == null){
            throw new OeUserException(OeUserException.Type.USER_NOT_EXIST);
        }
        if(!user.getUser_state().equals(type.getName())){
            throw new OeUserException(OeUserException.Type.USER_STATE_ERROR);
        }
        return user;
    }
}
