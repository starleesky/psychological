package cn.com.tsjx.common.web.interfaces;

import cn.com.tsjx.common.bean.entity.BaseEntity;

/**
 * 你确定要哈哈？
 * 
 * 
 * xyl (2015-11-20 下午4:36:24)
 */
public abstract interface IInterfaces {
	
	/**
	 * 
	 * 
	 * 
	 * xyl (2015-11-19 下午3:09:11)
	 */
	public static abstract interface ICallBack {
		/**
		 * 
		 * 
		 * @param objArr
		 * @return
		 * 
		 * xyl (2015-11-20 下午4:45:10)
		 */
		public abstract int callBack(Object ...objArr);
	}
	
	/**
	 * 
	 * 
	 * 
	 * xyl (2015-11-23 下午6:57:51)
	 */
	public static abstract interface IGetCallBack {
		/**
		 * 
		 * 
		 * @param objArr
		 * @return
		 * 
		 * xyl (2015-11-23 下午6:58:39)
		 */
		public abstract Object callBack(Object ...objArr);
	}
	
	/**
	 * 
	 * 
	 * 
	 * xyl (2015-11-20 下午6:12:34)
	 */
	public static abstract interface IGetOrder {
		/**
		 * 
		 * 
		 * @param orderCode
		 * @return
		 * 
		 * xyl (2015-11-21 上午10:38:14)
		 */
		public abstract /*IOrder*/BaseEntity<?> getOrder(String orderCode);
	}
	
}
