package com.openexpense.exception;

import com.openexpense.dto.OeResult;

/**用户异常定义
 *2016/07/06.
 *@author xjouyi@163.com
 *@version 0.1
 */
public class OeUserException extends OeException {
    @Override
    public OeResult getResult() {
        return OeResult.getFailResult(this.type.getName());
    }

    /**用户异常类型
     *2016/07/06.
     *@author xjouyi@163.com
     *@version 0.1
     */
    public enum Type {
        /**用户企业代码不能为空 U001*/
        USER_COMPANY_NULL("U001"),
        /**用户代码不能为空 U001*/
        USER_CODE_NULL("U002"),
        /**用户不存在 U001*/
        USER_NOT_EXIST("U003"),
        /**用户状态 U001*/
        USER_STATE_ERROR("U003");
        private String name;
        Type(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
    }

    OeUserException.Type type;

    /**根据用户异常类型创建用户异常对象
     * @param type OeUserException.Type 公司异常类型创
     */
    public OeUserException(OeUserException.Type type){
        this.type = type;
    }
}
