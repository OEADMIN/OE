package com.openexpense.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**企业实体类
 *2016/06/30.
 *@author xjouyi@163.com
 *@version 0.1
 */
@Table(name = "oe_company")
public class Company {

    /**企业id*/
    @Id
    @Column(name = "company_id")
    private String company_id;
    /**企业名称*/
    @Column(name = "company_name")
    private String company_name;
    /**企业域*/
    @Column(name = "company_domain")
    private String company_domain;
    /**企业状态*/
    @Column(name = "company_state")
    private String company_state;
    /**创建时间*/
    @Column(name = "create_date")
    private Date create_date;

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_domain() {
        return company_domain;
    }

    public void setCompany_domain(String company_domain) {
        this.company_domain = company_domain;
    }

    public String getCompany_state() {
        return company_state;
    }

    public void setCompany_state(String company_state) {
        this.company_state = company_state;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}
