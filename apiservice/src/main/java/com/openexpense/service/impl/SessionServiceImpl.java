package com.openexpense.service.impl;

import com.openexpense.exception.OeSessionException;
import com.openexpense.model.Session;
import com.openexpense.model.User;
import com.openexpense.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Hashtable;
import java.util.UUID;

/**session服务实现
 *2016/07/06.
 *@author xjouyi@163.com
 *@version 0.1
 */
@Service
public class SessionServiceImpl implements SessionService{
    private Hashtable<String,Session> sessionTable = new Hashtable<>();

    /** @see com.openexpense.service.SessionService#newSession(User) */
    @Override
    public String newSession(User user) {
        Session session = new Session();
        String id = UUID.randomUUID().toString();
        session.setSessionid(id);
        session.setUserid(user.getUser_id());
        session.setCompanyid(user.getCompany_id());
        session.setLastdate(new Date(System.currentTimeMillis()));
        sessionTable.put(id,session);
        return id;
    }

    /** @see com.openexpense.service.SessionService#getSession(String)  */
    public Session getSession(String id) throws OeSessionException {
        if (!sessionTable.containsKey(id)){
            throw new OeSessionException(OeSessionException.Type.SESSION_NOT_FIND);
        }
        Session session = sessionTable.get(id);
        session.setLastdate(new Date(System.currentTimeMillis()));
        return session;
    }

    /** @see com.openexpense.service.SessionService#removeSession(String) */
    public void removeSession(String id) {
        if (sessionTable.containsKey(id)){
            sessionTable.remove(id);
        }
    }
}
