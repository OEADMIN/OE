package com.openexpense.service;

import com.openexpense.exception.OeSessionException;
import com.openexpense.model.Session;
import com.openexpense.model.User;

/**Session服务
 *2016/07/06.
 *@author xjouyi@163.com
 *@version 0.1
 */
public interface SessionService {
    /**根据用户新建session
     * @param user 用户对象
     * @return sessionid String
     * @see com.openexpense.model.User
     */
    String newSession(User user);

    /**根据sessionid获取session 对象
     * @param id String sessionid
     * @return session 对象
     * @see com.openexpense.model.Session
     */
    Session getSession(String id) throws OeSessionException;


    /** 根据sessionid删除session
     * @param id String sessionid
     */
    void removeSession(String id);
}
