package cn.com.tsjx.common.util.pay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.tsjx.common.bean.entity.alipay.Detail;
import cn.com.tsjx.common.util.lang.MoneyUtils;



public class AliPayUtils {

	/**
	 * 过滤参数列表，除去参数列表中的空值和签名参数。
	 * @param params 参数列表
	 * @return
	 */
	public static Map<String, String> filterParams(Map<String, String> params) {
		Map<String, String> result = new HashMap<String, String>();
		if (params == null || params.size() <= 0) {
			return result;
		}
		for (String key : params.keySet()) {
			String value = params.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}

	/**
	 * 将参数列表元素排序，并按照“键=值”的模式用“&”字符拼接成字符串后返回。
	 * @param params 参数列表
	 * @return
	 */
	public static String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (i > 0) {
				builder.append("&");
			}
			builder.append(key).append("=").append(value);
		}
		return builder.toString();
	}

	/**
	 * 签名字符串
	 * @param text 需要签名的字符串
	 * @param key 密钥
	 * @param charset 编码格式
	 * @return 签名结果
	 */
	public static String sign(String text, String key, String charset) {
		return PayUtils.md5Hex(text + key, charset);
	}

	/**
	 * 验证签名
	 * @param text 需要签名的字符串
	 * @param sign 签名结果
	 * @param key 密钥
	 * @param charset 编码格式
	 * @return 签名结果
	 */
	public static boolean verify(String text, String sign, String key, String charset) {
		String mysign = PayUtils.md5Hex(text + key, charset);
		return mysign.equals(sign);
	}

	/**
	 * 获取支付宝批量转账返回数据。<br>
	 * 
	 * 成功的数据格式：<br>
	 * 流水号^收款方账号^收款账号姓名^付款金额^成功标识(S)^成功原因^支付宝内部流水号^完成时间
	 * 0315001^gonglei1@handsome.com.cn^龚本林^20.00^S^null^200810248427067^20081024143652<br>
	 * 
	 * 失败的数据格式：<br>
	 * 流水号^收款方账号^收款账号姓名^付款金额^失败标识(F)^失败原因^支付宝内部流水号^完成时间
	 * 0315006^xinjie_xj@163.com^星辰公司1^20.00^F^TXN_RESULT_TRANSFER_OUT_CAN_NOT_EQUAL_IN
	 * ^200810248427065^20081024143651
	 * 
	 * @param details 字符串数据
	 * @return
	 * @throws Exception
	 */
	public static List<Detail> parseDetails(String details) {
		if (details == null || details.length() == 0) {
			return new ArrayList<Detail>();
		}
		List<Detail> detailList = new ArrayList<Detail>();
		String[] detailArray = details.split("\\|");
		for (String detail : detailArray) {
			String[] array = detail.split("\\^");
			Detail dtl = new Detail();
			dtl.setTradeNo(array[0]);
			dtl.setAccntNo(array[1]);
			dtl.setAccntName(array[2]);
			dtl.setAmount(MoneyUtils.yuanToFen(Double.parseDouble(array[3])));
			dtl.setSuccess(array[4]);
			dtl.setReson(array[5]);
			dtl.setOutTradeNo(array[6]);
			dtl.setNotifyTime(parseFinishDate(array[7]));
			detailList.add(dtl);
		}
		return detailList;
	}

	public static String formatDetails(List<Detail> details) {
		StringBuilder builder = new StringBuilder();
		for (Detail detail : details) {
			builder.append(detail.getTradeNo()).append("^");
			builder.append(detail.getAccntNo()).append("^");
			builder.append(detail.getAccntName()).append("^");
			builder.append(MoneyUtils.format(MoneyUtils.fenToYuan(detail.getAmount()))).append("^");
			builder.append(detail.getSuccess()).append("^");
			builder.append(detail.getReson()).append("^");
			builder.append(detail.getOutTradeNo()).append("^");
			builder.append(formatNotifyTime(detail.getNotifyTime())).append("|");
		}
		if (builder.length() > 0) {
			builder.deleteCharAt(builder.length() - 1);
		}
		return builder.toString();
	}

	private static String formatNotifyTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(date);
	}

	private static Date parseFinishDate(String value) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			return format.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
