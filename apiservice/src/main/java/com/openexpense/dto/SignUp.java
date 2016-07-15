package com.openexpense.dto;

import com.openexpense.exception.OeExceptionType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**企业注册类型
 *2016/07/14.
 *@author xjouyi@163.com
 */
public class SignUp {

    /**企业域*/
    @NotEmpty(message = OeExceptionType.NOT_EMPTY)
    @Pattern(regexp = "^.*(?!@).*$",message = OeExceptionType.FORMART_ERROR)
    @Size(max = 20,message = OeExceptionType.TOO_LONG)
    private String cdomain;

    /**企业名*/
    @NotEmpty(message = OeExceptionType.NOT_EMPTY)
    @Size(max = 20,message = OeExceptionType.TOO_LONG)
    private String cname;

    /**用户编码*/
    @Pattern(regexp = "^.*(?!@).*$",message = OeExceptionType.FORMART_ERROR)
    @NotEmpty(message = OeExceptionType.NOT_EMPTY)
    private String ucode;

    /**用户姓名*/
    @NotEmpty(message = OeExceptionType.NOT_EMPTY)
    private String uname;

    /**用户Email*/
    @Email(message = OeExceptionType.FORMART_ERROR)
    @NotEmpty(message = OeExceptionType.NOT_EMPTY)
    private String uemail;
    /**用户密码*/
    @NotEmpty(message = OeExceptionType.NOT_EMPTY)
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
