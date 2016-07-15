package com.openexpense.dto;

import com.openexpense.exception.OeExceptionType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**企业登录类型
 *2016/07/15.
 *@author xjouyi@163.com
 */
public class SignIn {

    /**登录code*/
    @NotEmpty(message = OeExceptionType.NOT_EMPTY)
    @Pattern(regexp = "^.*(@).*$",message = OeExceptionType.FORMART_ERROR)
    private String logincode;

    /**密码*/
    @NotEmpty(message = OeExceptionType.NOT_EMPTY)
    private String pass;

    public String getLogincode() {
        return logincode;
    }

    public void setLogincode(String logincode) {
        this.logincode = logincode;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
