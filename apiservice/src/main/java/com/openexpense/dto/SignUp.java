package com.openexpense.dto;

import javax.validation.constraints.NotNull;

/**企业注册类型
 *2016/07/14.
 *@author xjouyi@163.com
 */
public class SignUp {

    /**企业域*/
    @NotNull(message = "xxxx")
    private String cdomain;
    /**企业名*/
    private String cname;
    /**用户编码*/
    private String ucode;
    /**用户姓名*/
    private String uname;
    /**用户Email*/
    private String uemail;
    /**用户密码*/
    private String upass;

    public String getCdomain() {
        return cdomain;
    }

    public void setCdomain(String cdomain) {
        this.cdomain = cdomain;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getUcode() {
        return ucode;
    }

    public void setUcode(String ucode) {
        this.ucode = ucode;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }
}
