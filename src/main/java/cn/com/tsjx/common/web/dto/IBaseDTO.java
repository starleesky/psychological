package cn.com.tsjx.common.web.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * 
 * xyl (2015-10-20 上午9:48:25)
 */
public abstract interface IBaseDTO {
	
	/**
	 * 
	 * 
	 * 
	 * xyl (2015-10-20 上午10:15:24)
	 */
	public static final class Data implements java.io.Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * 
		 */
		private /*static */final Map<String, Object> map = new HashMap</*String, Object*/>();
		
		/**
		 * 
		 * 
		 * @param key
		 * @param value
		 * 
		 * xyl (2015-10-20 上午10:26:38)
		 */
		public /*static */final void set(String key, Object value) {
			map.put(key, value);
		}
		
		/**
		 * 
		 * 
		 * @param key
		 * @return
		 * 
		 * xyl (2015-10-20 上午10:27:01)
		 */
		public /*static */final Object get(String key) {
			return map./*remove*/get(key); // get and remove
		}
		
	}
	
	/**
	 * 
	 */
	public static final String RETURN_PATH = "returnPath";
	
	/**
	 * 
	 * 
	 * @return
	 * 
	 * xyl (2015-10-20 上午10:11:36)
	 */
	public String getReturnPath();
	
	/**
	 * 
	 * 
	 * @param returnPath
	 * 
	 * xyl (2015-10-20 上午10:11:42)
	 */
	public void setReturnPath(String returnPath);
	
}
