package cn.com.tsjx.common.util.lang;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class CodeUtils {

	public static String generateCode(String type) {
		String value = RandomStringUtils.randomNumeric(8);
		return StringUtils.leftPad(value, 8, "0");
	}

	public static String authIdToCode(Long id) {
		Long value = id + 10000; // 为了生成的编码有四个字符长度,加上一个固定数值
		return Long.toHexString(value).toUpperCase();
	}
}
