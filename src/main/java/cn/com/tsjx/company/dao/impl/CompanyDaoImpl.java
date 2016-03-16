package cn.com.tsjx.company.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.company.dao.CompanyDao;
import cn.com.tsjx.company.entity.Company;

@Repository("companyDao")
public class CompanyDaoImpl extends BaseDaoImpl<Company, Long> implements CompanyDao {

}
