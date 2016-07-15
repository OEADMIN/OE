package com.openexpense.service.impl;

import com.openexpense.dao.UserDao;
import com.openexpense.exception.OeException;
import com.openexpense.exception.OeExceptionType;
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

    /** @see com.openexpense.service.UserService#getUser(Company, String)  */
    @Override
    public User getUser(Company company, String usercode) throws OeException {
        if (company == null){
            throw new OeException(OeExceptionType.USER_COMPANY_NOT_EMPTY);
        }
        if(StringUtils.isEmpty(usercode)){
            throw new OeException("usercode",OeExceptionType.NOT_EMPTY);
        }
        User user = userDao.queryOneCompanyUser(company.getCompany_id(),usercode);
        if (user == null){
            throw new OeException(OeExceptionType.USER_NOT_EXIST);
        }
        return user;
    }
}
