package cn.com.tsjx.common.constants;

/**
 * 
 * 
 * 
 * xyl (2015-9-25 下午5:15:58)
 */
public /*static */abstract interface IConstant {
	
	/**
	 * 度号
	 */
	public static final char DEGREE = ',';
	
	/**
	 * UTF-8
	 */
	public static final String UTF_8 = "UTF-8";
	
	/**
	 * ISO-8859-1
	 */
	public static final String ISO_8859_1 = "ISO-8859-1";
	
	/**
	 * MD5
	 */
	public static final String MD5 = "MD5";
	
	/**
	 * RSA
	 */
	public static final String RSA = "RSA";
	
	/**
	 * error
	 */
	public static final String ERROR = "error";
	
	/**
	 * 
	 */
	public static final int MINUS_ONE = -1;
	
	/**
	 * 
	 */
	public static final int ZERO = 0;
	
	/**
	 * 
	 */
	public static final int ONE = 1;
	
	/**
	 * 
	 */
	public static final String SET = "set";
	
	/**
	 * 
	 */
	public static final String GET = "get";
	
	/**
	 * 
	 * 
	 * 
	 * xyl (2015-9-25 下午5:18:31)
	 */
	public static abstract interface IResultCode {
		
		/**
		 * 
		 */
		public static final String SUCCESS = "SUCCESS";
		
		/**
		 * 
		 */
		public static final String FAIL = "FAIL";
		
		/**
		 * 成功
		 */
		public static final int SUCCESS_CODE = 1;
		
		/**
		 * 失败
		 */
		public static final int FAIL_CODE = 0;
		
	}
	
	/**
	 * 平台编码
	 * 
	 * 
	 * xyl (2015-12-9 下午4:35:29)
	 */
	public static abstract interface Platform {
		
		/**
		 * 电脑
		 */
		public static final int COMPUTER = 1;
		
		/**
		 * 手机
		 */
		public static final int MOBILE = 2;
		
	};
	
}
