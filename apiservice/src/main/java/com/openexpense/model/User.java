package com.openexpense.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**用户实体类
 *2016/07/06.
 *@author xjouyi@163.com
 *@version 0.1
 */
@Table(name = "oe_user")
public class User {
    /**用户id*/
    @Id
    @Column(name = "user_id")
    private String user_id;
    /**用户代码*/
    @Column(name = "user_code")
    private String user_code;
    /**用户姓名*/
    @Column(name = "user_name")
    private String user_name;
    /**用户密码*/
    @Column(name = "user_pass")
    private String user_pass;
    /**用户email*/
    @Column(name = "user_email")
    private String user_email;
    /**用户手机*/
    @Column(name = "user_phone")
    private String user_phone;
    /**用户状态*/
    @Column(name = "user_state")
    private String user_state;
    /**是否创始人*/
    @Column(name = "is_founder")
    private boolean is_founder;
    /**所属公司*/
    @Column(name = "company_id")
    private String company_id;
    /**创建时间*/
    @Column(name = "create_date")
    private Date create_date;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public boolean is_founder() {
        return is_founder;
    }

    public void setIs_founder(boolean is_founder) {
        this.is_founder = is_founder;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getUser_state() {
        return user_state;
    }

    public void setUser_state(String user_state) {
        this.user_state = user_state;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
}
