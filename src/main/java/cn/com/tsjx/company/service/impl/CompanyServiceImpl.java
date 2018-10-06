package cn.com.tsjx.company.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.company.dao.CompanyDao;
import cn.com.tsjx.company.entity.Company;
import cn.com.tsjx.company.service.CompanyService;

@Service("companyService")
public class CompanyServiceImpl extends BaseServiceImpl<Company, Long> implements CompanyService {

	@Resource
	private CompanyDao companyDao;

	@Override
	protected BaseDao<Company, Long> getBaseDao() {
		return this.companyDao;
	}
}
