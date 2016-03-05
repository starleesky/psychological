package cn.com.tsjx.customer.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.customer.dao.CustomerDao;
import cn.com.tsjx.customer.entity.Customer;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer, Long> implements CustomerDao {

}
