package com.openexpense.service;

import com.openexpense.exception.OeUserException;
import com.openexpense.model.Company;
import com.openexpense.model.User;


/**用户服务
 *2016/07/06.
 *@author xjouyi@163.com
 *@version 0.1
 */
public interface UserService {

    /**用户状态类型
     *2016/07/06.
     *@author xjouyi@163.com
     *@version 0.1
     */
    enum Type {
        /**正常 00*/
        NORMAL ("00");
        private String name;
        Type(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
    }

    /**根据企业对象,用户代码,用户状态获取用户对象
     * @param company Company 企业对象
     * @param usercode String 用户代码
     * @param type UserService.Type 用户状态
     * @return user 用户对象
     * @see com.openexpense.model.Company
     */
    User getUser(Company company,String usercode,UserService.Type type) throws OeUserException;
}
