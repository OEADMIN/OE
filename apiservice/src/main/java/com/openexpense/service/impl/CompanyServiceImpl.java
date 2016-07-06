package com.openexpense.service.impl;

import com.openexpense.dao.CompanyDao;
import com.openexpense.exception.OeCompanyException;
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
     * @see com.openexpense.service.CompanyService#getCompanyByDomain(String, Type)
     */
    @Override
    public Company getCompanyByDomain(String domain, CompanyService.Type type) throws OeCompanyException {
        Company company = companyDao.queryByDomain(domain);
        if (company == null){
            throw new OeCompanyException(OeCompanyException.Type.COMPANY_NOT_FIND);
        }
        if (!company.getCompany_state().equals(type.getName())){
            throw new OeCompanyException(OeCompanyException.Type.COMPANY_STATE_ERROR);
        }
        return company;
    }
}
