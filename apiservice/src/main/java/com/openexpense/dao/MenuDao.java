package com.openexpense.dao;

import com.openexpense.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jinyi on 16/7/26.
 */
public interface MenuDao extends BaseDao {
    List<Menu> getMenu(@Param("state")String state);
    List<Menu> getMenuByUser(@Param("userid")String userid);
}
