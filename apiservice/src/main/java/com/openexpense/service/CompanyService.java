package com.openexpense.service;

import com.openexpense.dto.SignUp;
import com.openexpense.model.Company;

/**企业服务
 *2016/07/06.
 *@author xjouyi@163.com
 *@version 0.1
 */
public interface CompanyService {

    /**企业状态类型
     *2016/07/06.
     *@author xjouyi@163.com
     *@version 0.1
     */
    enum Type {
        /**正常 00*/
        NORMAL ("00");
        private String name;
        Type(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
    }

    /**根据企业域和企业状态获取企业
     * @param domain 企业域 如:openexpence.com
     * @return Company对象
     */
    Company getCompanyByDomain(String domain);

    /**根据企业域和企业状态获取企业
     * @param signUp SignUp 添加企业
     * @return Company对象
     */
    Company addCompany(SignUp signUp);
}
