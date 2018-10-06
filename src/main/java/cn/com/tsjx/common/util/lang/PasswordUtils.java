package cn.com.tsjx.common.util.lang;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 密码处理工具类
 * @author liwei
 */
public class PasswordUtils {

	/**
	 * 老系统密码格式，不可以修改。
	 * @param pass 待加密的密码值
	 * @return
	 */
	public static String encode(String pass) {
		return DigestUtils.md5Hex(DigestUtils.md5Hex(pass));
	}

	/**
	 * 验证密码是否有效
	 * @param encPass 密文
	 * @param rawPass 原文
	 * @return
	 */
	public static boolean isValid(String encPass, String rawPass) {
		String pass1 = "" + encPass;
		String pass2 = encode(rawPass);

		return pass1.equalsIgnoreCase(pass2);
	}

	public static void main(String[] args) {
		// admin123 : 0c909a141f1f2c0a1cb602b0b2d7d050
		System.out.println(encode("admin123"));
		System.out.println(isValid("0c909a141f1f2c0a1cb602b0b2d7d050", "admin123"));
	}
}
