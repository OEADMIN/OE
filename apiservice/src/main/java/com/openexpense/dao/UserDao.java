package com.openexpense.dao;

import com.openexpense.model.User;
import org.apache.ibatis.annotations.Param;

/**用户表操作dao
 *2016/07/06.
 *@author xjouyi@163.com
 */
public interface UserDao extends BaseDao {

    /**根据用户id获取用户对象
     *@param userid string 用户id
     *@return User 对应用户
     */
    User queryOne(@Param("userid")String userid);

    User queryOneByEmail(@Param("user_email")String email);

    /**根据企业和用户代码获取用户对象
     *@param company_id string 用户id
     *@param user_code string 用户id
     *@return User 对应用户
     */
    User queryOneCompanyUser(@Param("company_id")String company_id,@Param("user_code") String user_code);
}
