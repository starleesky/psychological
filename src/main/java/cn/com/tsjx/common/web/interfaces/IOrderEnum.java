package cn.com.tsjx.common.web.interfaces;

/**
 * 
 * 
 * 
 * xyl (2015-11-24 下午5:14:23)
 */
public /*static */abstract interface IOrderEnum {
	
	/**
	 * 
	 * 
	 * @return
	 * 
	 * xyl (2015-11-24 下午5:18:49)
	 */
	public abstract Long getUnPay();
	
	/**
	 * 
	 * 
	 * @return
	 * 
	 * xyl (2015-11-24 下午5:18:46)
	 */
	public abstract Long getPay();
	
	/**
	 * 
	 * 
	 * @return
	 * 
	 * xyl (2015-11-24 下午5:18:43)
	 */
	public abstract Long getComplete();
	
}
