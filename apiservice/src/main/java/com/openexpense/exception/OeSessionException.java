package com.openexpense.exception;

import com.openexpense.dto.OeResult;

/**session异常定义
 *2016/07/02.
 *@author xjouyi@163.com
 *@version 0.1
 */
public class OeSessionException extends OeException {
    @Override
    public OeResult getResult() {
        return null;
    }

    /**session异常类型
     *2016/07/02.
     *@author xjouyi@163.com
     *@version 0.1
     */
    public enum Type {
        /**session不存在 S001*/
        SESSION_NOT_FIND("S001");
        private String name;
        Type(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
    }

    OeSessionException.Type type;
    /**根据session异常类型创建session异常对象
     * @param type OeSessionException.Type 公司异常类型创
     */
    public OeSessionException(OeSessionException.Type type){
        this.type = type;
    }

}
