package com.openexpense.exception;

/**异常类型
 *2016/07/02.
 *@author xjouyi@163.com
 *@version 0.1
 */
public enum OeExceptionType {
    ;
    /**不能为空 C003*/
    public static final String NOT_EMPTY = "NOT_EMPTY";
    /**式错误 C005*/
    public static final String FORMART_ERROR = "FORMART_ERROR";
    /**太长 C006*/
    public static final String TOO_LONG = "TOO_LONG";

    /**企业不存在 C001*/
    public static final String COMPANY_NOT_FIND = "COMPANY_NOT_FIND1";
    /**企业状态错误 C002*/
    public static final String COMPANY_NOT_ACTIVE = "COMPANY_NOT_ACTIVE";

    public static final String COMPANY_DOMAIN_EXISTS = "COMPANY_DOMAIN_EXISTSE";

    /**用户密码错误 H003*/
    public static final String HOST_PASS_ERROR = "HOST_PASS_ERROR";
    /**session不存在 S001*/
    public static final String SESSION_NOT_FIND = "SESSION_NOT_FIND";
    /**用户企业代码不能为空 U001*/
    public static final String USER_COMPANY_NOT_EMPTY = "USER_COMPANY_NOT_EMPTY";
    /**用户代码不能为空 U002*/
    public static final String USER_CODE_NOT_EMPTY = "USER_CODE_NOT_EMPTY";
    /**用户状态错误 U004*/
    public static final String USER_CODE_EXISTS = "USER_CODE_EXISTS";
    /**用户不存在 U002*/
    public static final String USER_NOT_EXIST = "USER_NOT_EXIST";
    /**用户状态错误 U004*/
    public static final String USER_NOT_ACTIVE = "USER_NOT_EXIST";

    private String name;
    OeExceptionType(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
