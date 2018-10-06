package cn.com.tsjx.common.web.enums;

/**
 * 
 * 
 * 
 * xyl (2015-12-2 下午12:16:38)
 */
public enum PayStatus {

	UNPAY(0L, "未支付"), 
	PAY(1L, "已支付");

	public Long code;
	public String name;

	PayStatus(Long code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String valueOfCode(Long code) {
		for (PayStatus e : PayStatus.values()) {
			if (e.code.equals(code)) {
				return e.name;
			}
		}
		return null;
	}

	public Long getCode() {
		return code;
	}

	public String getLabel() {
		return name;
	}
}
