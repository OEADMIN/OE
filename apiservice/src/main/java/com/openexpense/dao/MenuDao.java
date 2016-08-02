package com.openexpense.dao;

import com.openexpense.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**菜单表操作dao
 *2016/08/02.
 *@author xjouyi@163.com
 */
public interface MenuDao extends BaseDao {
    /**根据菜单状态获取所有菜单
     *@param  state 菜单状态
     *@return 菜单列表
     */
    List<Menu> getMenu(@Param("state")String state);

    /**根据用户id获取所有有权限的菜单
     *@param  userid 用户id
     *@return 菜单列表
     */
    List<Menu> getMenuByUser(@Param("userid")String userid);
}
