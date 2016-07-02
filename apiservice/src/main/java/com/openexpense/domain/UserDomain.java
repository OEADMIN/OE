package com.openexpense.domain;

import com.openexpense.dao.UserDao;
import com.openexpense.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jinyi on 16/7/2.
 */
@Service
public class UserDomain {

    @Autowired
    UserDao userDao;

    public String userLogin(String userid,String pass){
        User user = userDao.queryOne(userid);
        if (user != null){
            return user.getUser_name();
        }else{
            return "false";
        }
    }
}
