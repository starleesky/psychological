package cn.com.tsjx.common.util.pay;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 支付开发工具类
 * @author liwei
 */
public class PayUtils {

	/**
	 * 根据字符串和编码，生成MD5摘要
	 * @param text 字符串
	 * @param charset 编码
	 * @return
	 */
	public static String md5Hex(String text, String charset) {
		byte[] bytes;
		if (charset == null || "".equals(charset)) {
			bytes = text.getBytes();
		} else {
			bytes = text.getBytes(Charset.forName(charset));
		}
		return DigestUtils.md5Hex(bytes);
	}

	/**
	 * 根据Map数据生成字符串，KEY会自动排序。<br>
	 * 格式：k1=v1&k2=v2&k3=v3
	 * @param params
	 * @param connector
	 * @return
	 */
	public static String join(Map<String, String> params) {
		params = new TreeMap<String, String>(params);
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.append(entry.getKey());
			builder.append('=');
			if (entry.getValue() != null) {
				builder.append(entry.getValue());
			}
			builder.append('&');
		}
		if (builder.length() > 0) {
			builder.deleteCharAt(builder.length() - 1);
		}
		return builder.toString();
	}

	private static final String DATE_TIME_PATTERN = "yyyyMMddHHmmss";

	public static String formatDateTime(Date date) {
		return DateFormatUtils.format(date, DATE_TIME_PATTERN);
	}

	public static Date parseDateTime(String value) {
		try {
			return DateUtils.parseDate(value, DATE_TIME_PATTERN);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
