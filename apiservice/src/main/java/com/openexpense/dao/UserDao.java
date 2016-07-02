package com.openexpense.dao;

import com.openexpense.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User queryOne(@Param("userid")String userid);
}
