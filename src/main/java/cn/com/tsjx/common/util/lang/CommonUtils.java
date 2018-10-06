/**
 * @author wangxf
 * 2014-6-30
 */
package cn.com.tsjx.common.util.lang;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具类
 * @author wangxf
 *
 */
public class CommonUtils {
	
	/**
	 * 判断List不为空
	 * @author wangxf
	 * @param list
	 * @return 非空　ｔｒｕｅ　　　　空：ｆａｌｓｅ
	 */
	public static boolean isEmpty(List<?> list){
		return  !(null != list && list.size() > 0);
	}
	
	
	public static void main(String[] args) {
		@SuppressWarnings("rawtypes")
		List s = new ArrayList();
		System.out.println(!isEmpty(s));
	}
}
