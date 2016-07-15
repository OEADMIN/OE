package com.openexpense.service.impl;

import com.openexpense.dao.CompanyDao;
import com.openexpense.exception.OeException;
import com.openexpense.exception.OeExceptionType;
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
    public Company getCompanyByDomain(String domain) throws OeException {
        Company company = companyDao.queryByDomain(domain);
        if (company == null){
            throw new OeException(OeExceptionType.COMPANY_NOT_FIND);
        }
        return company;
    }
}
