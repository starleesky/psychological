package cn.com.tsjx.common.util;

import java.util.List;

/**
 * 公用工具类
 * @Type CommonUtils
 * @Desc 
 * @author hefan
 * @date 2015年5月1日
 * @Version V1.0
 */
public class CommonUtils {

	/**
	 * list是否为空，  true：空      fasle：非空
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(List<?> list) {
		if (null == list || list.size() == 0) {
			return true;
		}
		
		return false;
	}
}
