package cn.com.tsjx.common.enums;


public enum Week implements IEnum<String> {

	/**
	 * 星期一，值：
	 */
	MON("MON", "星期一"),
	/**
	 * 星期二，值：
	 */
	TUES("TUES", "星期二"),
	/**
	 * 星期二，值：
	 */
	WED("WED", "星期三"),
	/**
	 * 星期二，值：
	 */
	THUR("THUR", "星期四"),
	/**
	 * 星期二，值：
	 */
	FRI("FRI", "星期五"),
	/**
	 * 星期二，值：
	 */
	SAT("SAT", "星期六"),
	/**
	 * 星期二，值：sun
	 */
	SUN("SUN", "星期天");

	private final String value;
	private final String label;

	private Week(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public String value() {
		return value;
	}

	public String label() {
		return this.label;
	}
}
