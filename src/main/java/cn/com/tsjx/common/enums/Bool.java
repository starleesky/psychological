package cn.com.tsjx.common.enums;


/**
 * Boolean枚举
 * 
 * @Type Bool
 * @Desc 
 * @author hefan
 * @date 2015年5月1日
 * @Version V1.0
 */
public enum Bool implements IEnum<String> {

	/**
	 * 启用，值：T
	 */
	TRUE("T", "是"),

	/**
	 * 禁用，值：F
	 */
	FALSE("F", "否");

	public final String value;
	public final String label;

	private Bool(String value, String label) {
		this.value = value;
		this.label = label;
	}

	public String value() {
		return this.value;
	}

	public String label() {
		return this.label;
	}
}
