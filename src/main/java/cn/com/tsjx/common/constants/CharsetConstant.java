package cn.com.tsjx.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符集常量
 * 
 * @author crazy_cabbage
 * 
 */
public interface CharsetConstant {
	/**
	 * UTF-8 编码
	 */
	String UTF_8 = "utf-8";
	/**
	 * GBK 编码
	 */
	String GBK = "gbk";
	/**
	 * ISO-8859-1 编码
	 */
	String ISO_8859_1 = "iso-8859-1";
	
	Map<String, String> INFO =new HashMap<String, String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 7115248652095488906L;

	{
		put(UTF_8, "UTF-8编码");
		put(GBK, "GBK编码");
		put(ISO_8859_1, "ISO-8859-1编码");
	}};
}
