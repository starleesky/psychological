package cn.com.tsjx.common.support;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Three
 * @version 创建时间：2013年11月29日 上午9:49:14 
 */

public class MultipleDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		System.out.println(CustomerContextHolder.getCustomerType());
		return CustomerContextHolder.getCustomerType();
	}

}
