package cn.com.tsjx.common.util.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author GY
 * @Description: TODO
 * @date 2013-10-16 下午4:27:32
 */
public class Validator {
	// ------------------常量定义
	/**
	 * Email正则表达式=^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$
	 */
	public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

	/**
	 * 电话号码正则表达式=
	 * (^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|
	 * (^0?1[35]\d{9}$)
	 */
	public static final String PHONE = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)";
	/**
	 * 手机号码正则表达式=^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])\d{8}$
	 */
	public static final String MOBILE = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";

	/**
	 * IP地址正则表达式
	 * ((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\
	 * d|[01]?\\d?\\d))
	 */
	public static final String IPADDRESS = "((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))";

	/**
	 * Integer正则表达式 ^-?(([1-9]\d*$)|0)
	 */
	public static final String INTEGER = "^-?(([1-9]\\d*$)|0)";
	/**
	 * 正整数正则表达式 >=0 ^[1-9]\d*|0$
	 */
	public static final String INTEGER_NEGATIVE = "^-[1-9]\\d*|0$";
	/**
	 * 负整数正则表达式 <=0 ^-[1-9]\d*|0$
	 */
	public static final String INTEGER_POSITIVE = "^[1-9]\\d*|0$";
	/**
	 * Double正则表达式 ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$
	 */
	public static final String DOUBLE = "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";
	/**
	 * 正Double正则表达式 >=0 ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$　
	 */
	public static final String DOUBLE_NEGATIVE = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";
	/**
	 * 负Double正则表达式 <= 0 ^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$
	 */
	public static final String DOUBLE_POSITIVE = "^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";
	/**
	 * 年龄正则表达式 ^(?:[1-9][0-9]?|1[01][0-9]|120)$ 匹配0-120岁
	 */
	public static final String AGE = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
	/**
	 * 邮编正则表达式 [1-9]\d{5}(?!\d) 国内6位邮编
	 */
	public static final String CODE = "[1-9]\\d{5}(?!\\d)";
	/**
	 * 匹配由数字、26个英文字母或者下划线组成的字符串 ^\w+$
	 */
	public static final String STR_ENG_NUM_ = "^\\w+$";
	/**
	 * 匹配由数字和26个英文字母组成的字符串 ^[A-Za-z0-9]+$
	 */
	public static final String STR_ENG_NUM = "^\\w+$";
	/**
	 * 匹配由26个英文字母组成的字符串 ^[A-Za-z]+$
	 */
	public static final String STR_ENG = "^[A-Za-z]+$";
	/**
	 * 匹配中文字符 ^[\u0391-\uFFE5]+$
	 */
	public static final String STR_CHINA = "^[\u0391-\uFFE5]+$";
	/**
	 * 过滤特殊字符串正则 regEx=
	 * "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
	 */
	public static final String STR_SPECIAL = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
	/**
	 * 只能输英文 数字 中文 ^[a-zA-Z0-9\u4e00-\u9fa5]+$
	 */
	public static final String STR_ENG_CHA_NUM = "^[a-zA-Z0-9\u4e00-\u9fa5]+$";
	/** 
     *   
     */
	/***
	 * 日期正则 支持： YYYY-MM-DD YYYY/MM/DD YYYY_MM_DD YYYYMMDD YYYY.MM.DD的形式
	 */
	public static final String DATE_ALL = "((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(10|12|0?[13578])([-\\/\\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(11|0?[469])([-\\/\\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(0?2)([-\\/\\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([3579][26]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$))";
	/***
	 * 日期正则 支持： YYYY-MM-DD
	 */
	public static final String DATE_FORMAT1 = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
	/**
	 * URL正则表达式 匹配 http www ftp
	 */
	public static final String URL = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?"
			+ "(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*"
			+ "(\\w*:)*(\\w*\\+)*(\\w*\\.)*"
			+ "(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";

	/**
	 * 身份证正则表达式
	 */
	public static final String IDCARD = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})"
			+ "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}"
			+ "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";
	public static final String IDCARD18 ="\\d{17}[Xx0-9]";
	public static final String IDCARD15 ="\\d{14}[Xx0-9]";
	/**
	 * 1.匹配科学计数 e或者E必须出现有且只有一次 不含Dd 正则
	 * ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]
	 * ?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))$
	 */
	public final static String SCIENTIFIC_A = "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))$";
	/**
	 * 2.匹配科学计数 e或者E必须出现有且只有一次 结尾包含Dd 正则
	 * ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012
	 * ]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))[dD]?$
	 */
	public final static String SCIENTIFIC_B = "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))[dD]?$";
	/**
	 * 3.匹配科学计数 是否含有E或者e都通过 结尾含有Dd的也通过（针对Double类型） 正则
	 * ^[-+]?(\d+(\.\d*)?|\.\d+)([
	 * eE]([-+]?([012]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$
	 */
	public final static String SCIENTIFIC_C = "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$";
	/**
	 * 4.匹配科学计数 是否含有E或者e都通过 结尾不含Dd 正则
	 * ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]?
	 * \d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?$
	 */
	public final static String SCIENTIFIC_D = "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?$";


	/**
	 * 判断字段是否为Email 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmail(Object str) {
		return Regular(str, EMAIL);
	}

	/**
	 * 判断是否为电话号码 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isPhone(Object str) {
		return Regular(str, PHONE);
	}

	/**
	 * 判断是否为手机号码 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isMobile(Object str) {
		return Regular(str, MOBILE);
	}

	/**
	 * 判断是否为Url 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isUrl(Object str) {
		return Regular(str, URL);
	}

	/**
	 * 判断是否为IP地址 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isIpaddress(Object str) {
		return Regular(str, IPADDRESS);
	}

	/**
	 * 判断字段是否为数字 正负整数 正负浮点数 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNumber(Object str) {
		return Regular(str, DOUBLE);
	}

	/**
	 * 判断字段是否为INTEGER 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isInteger(Object str) {
		return Regular(str, INTEGER);
	}
	
	/**
	 * 判断字段是否为正整数,正则表达式 >=0 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isIntPositive(Object str) {
		return Regular(str, INTEGER_POSITIVE);
	}

	/**
	 * 判断字段是否为负整数,正则表达式 <=0 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isIntNegative(Object str) {
		return Regular(str, INTEGER_NEGATIVE);
	}

	/**
	 * 判断字段是否为DOUBLE 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isDouble(Object str) {
		return Regular(str, DOUBLE);
	}

	/**
	 * 判断字段是否为正浮点数正则表达式 >=0 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isDOUBLE_NEGATIVE(Object str) {
		return Regular(str, DOUBLE_NEGATIVE);
	}

	/**
	 * 判断字段是否为负浮点数正则表达式 <=0 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isDOUBLE_POSITIVE(Object str) {
		return Regular(str, DOUBLE_POSITIVE);
	}

	/**
	 * 判断字段是否为日期 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isDate(Object str) {
		return Regular(str, DATE_ALL);
	}

	/**
	 * 验证2010-12-10
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDate1(Object str) {
		return Regular(str, DATE_FORMAT1);
	}

	/**
	 * 判断字段是否为年龄 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isAge(Object str) {
		return Regular(str, AGE);
	}

	/**
	 * 判断字段是否为身份证 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isIdCard(Object str) {
		if(null == str){
			return false;
		}
		String idCard = str.toString();
		if (StringUtils.isEmpty(idCard))
			return false;
		if (idCard.trim().length() == 15 || idCard.trim().length() == 18) {
			return Regular(idCard, IDCARD);
		} else {
			return false;
		}

	}
	
	/**
	 * 判断字段是否为身份证 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isIdCard2(Object str) {
		if(null == str){
			return false;
		}
		String idCard = str.toString();
		if (StringUtils.isEmpty(idCard))
			return false;
		if (idCard.trim().length() == 15) {
			return Regular(idCard, IDCARD15);
		} else if (idCard.trim().length() == 18) {
			return Regular(idCard, IDCARD18);
		}
		return false;
	}

	/**
	 * 判断字段是否为邮编 符合返回ture
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isCode(Object str) {
		return Regular(str, CODE);
	}

	/**
	 * 判断字符串是不是全部是汉字
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isChina(Object str) {
		return Regular(str, STR_CHINA);
	}

	/**
	 * 判断字符串是不是全部是英文字母
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isEnglish(Object str) {
		return Regular(str, STR_ENG);
	}

	/**
	 * 判断字符串是不是全部是英文字母+数字
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isENG_NUM(Object str) {
		return Regular(str, STR_ENG_NUM);
	}

	/**
	 * 判断字符串是不是全部是英文字母+数字+下划线
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isENG_NUM_(Object str) {
		return Regular(str, STR_ENG_NUM_);
	}
	
	/**
	 * 判断字符串是不是全部是英文 数字 中文 
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isSTR_ENG_CHA_NUM(Object str) {
		return Regular(str, STR_ENG_CHA_NUM);
	}

	/**
	 * 过滤特殊字符串 返回过滤后的字符串
	 * 
	 * @param str
	 * @return boolean
	 */
	public static String filterStr(String str) {
		Pattern p = Pattern.compile(STR_SPECIAL);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 匹配是否符合正则表达式pattern 匹配返回true
	 * 
	 * @param str
	 *            匹配的字符串
	 * @param pattern
	 *            匹配模式
	 * @return boolean
	 */
	private static boolean Regular(Object oStr, String pattern) {
		if (null == oStr)
			return false;
		String str = oStr.toString();
		if(StringUtils.isBlank(str)){
			return false;
		}
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}
}