package com.openexpense.dao;

import com.openexpense.model.Company;
import org.apache.ibatis.annotations.Param;


/**企业表操作dao
 *2016/07/06.
 *@author xjouyi@163.com
 */
public interface CompanyDao {

    /**根据企业域获取企业对象
     *@param domain string 企业域(openexpense.com)
     *@return Company 对应企业
     */
    Company queryByDomain(@Param("domain")String domain);
}
