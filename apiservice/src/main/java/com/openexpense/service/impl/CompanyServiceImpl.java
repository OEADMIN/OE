package com.openexpense.service.impl;

import com.openexpense.dao.CompanyDao;
import com.openexpense.dto.SignUp;
import com.openexpense.model.Company;
import com.openexpense.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**企业服务实现
 *2016/07/06.
 *@author xjouyi@163.com
 *@version 0.1
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyDao companyDao;

    /**
     * @see com.openexpense.service.CompanyService#getCompanyByDomain(String)
     */
    @Override
    public Company getCompanyByDomain(String domain) {
        return companyDao.queryByDomain(domain);
    }

    @Override
    public Company addCompany(SignUp signUp) {
        return null;
    }
}
