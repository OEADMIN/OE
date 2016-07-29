package com.openexpense.model;

import java.util.Date;

/**session实体类
 *2016/07/06.
 *@author xjouyi@163.com
 *@version 0.1
 */
public class Session {
    /**sessionid*/
    private String sessionid;
    /**用户对象*/
    private User user;
    /**用户所属公司*/
    private String companyid;
    /**最后操作时间*/
    private Date lastdate;
    public Session(){

    }

    public Session(String sessionid){
        this.sessionid = sessionid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public Date getLastdate() {
        return lastdate;
    }

    public void setLastdate(Date lastdate) {
        this.lastdate = lastdate;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
