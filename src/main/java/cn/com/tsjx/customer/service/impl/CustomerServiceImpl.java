package cn.com.tsjx.customer.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.customer.dao.CustomerDao;
import cn.com.tsjx.customer.entity.Customer;
import cn.com.tsjx.customer.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long> implements CustomerService {

	@Resource
	private CustomerDao customerDao;

	@Override
	protected BaseDao<Customer, Long> getBaseDao() {
		return this.customerDao;
	}
}
