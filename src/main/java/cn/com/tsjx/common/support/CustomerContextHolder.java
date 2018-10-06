package cn.com.tsjx.common.support;

/**
 * @author Three
 * @version 创建时间：2013年11月29日 上午9:50:13
 */

public abstract class CustomerContextHolder {
	
	public final static String DATA_SOURCE_ORDER_ORACLE = "dataSourceOracle_order";
	public final static String DATA_SOURCE_PWB2_ORACLE = "dataSourceOracle_pwb2";
	public final static String DATA_SOURCE_TRAVEL_MYSQL = "dataSourceMySQL_travel";
	public final static String DATA_SOURCE_USER_MYSQL = "dataSourceMySql_user";

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setCustomerType(String customerType) {
		contextHolder.set(customerType);
	}

	public static String getCustomerType() {
		return contextHolder.get();
	}

	public static void clearCustomerType() {
		contextHolder.remove();
	}
}
