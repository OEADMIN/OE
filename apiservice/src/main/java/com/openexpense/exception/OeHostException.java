package com.openexpense.exception;

import com.openexpense.dto.OeResult;

/**服务异常定义
 *2016/07/02.
 *@author xjouyi@163.com
 *@version 0.1
 */
public class OeHostException extends OeException {
    @Override
    public OeResult getResult() {
        return OeResult.getFailResult(this.type.getName());
    }

    /**服务异常类型
     *2016/07/02.
     *@author xjouyi@163.com
     *@version 0.1
     */
    public enum Type {
        /**用户名或密码为空 H001*/
        HOST_USER_PASS_NULL("H001"),
        /**用户名格式错误 H002*/
        HOST_CODE_FORMART_ERROR("H002"),
        /**用户密码错误 H003*/
        HOST_PASS_ERROR("H003");
        private String name;
        Type(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
    }

    OeHostException.Type type;

    /**根据服务异常类型创建服务异常对象
     * @param type OeHostException.Type 公司异常类型创
     */
    public OeHostException(OeHostException.Type type){
        this.type = type;
    }


}
