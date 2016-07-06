package com.openexpense.exception;

import com.openexpense.dto.OeResult;

/**公司异常定义
 *2016/07/02.
 *@author xjouyi@163.com
 *@version 0.1
 */
public class OeCompanyException extends OeException {

    /**获取返回对象
     *@return 根据异常获取返回对象
     */
    @Override
    public OeResult getResult() {
        return OeResult.getFailResult(this.type.getName());
    }

    /**公司异常类型
     *2016/07/02.
     *@author xjouyi@163.com
     *@version 0.1
     */
    public enum Type {
        /**企业不存在 C001*/
        COMPANY_NOT_FIND("C001"),
        /**企业状态错误 C002*/
        COMPANY_STATE_ERROR("C002");

        private String name;
        Type(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
    }

    OeCompanyException.Type type;

    /**根据公司异常类型创建公司异常对象
     * @param type OeCompanyException.Type 公司异常类型创
     */
    public OeCompanyException(OeCompanyException.Type type){
        this.type = type;
    }
}
