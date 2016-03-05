package cn.com.tsjx.common.enums;


/**
 * 数据是否删除标识。
 */
public enum Deleted implements IEnum<String> {

	/**
	 * 未删除，值：F
	 */
	NO("F", "未删除"),

	/**
	 * 已删除，值：T
	 */
	YES("T", "已删除");

	public final String value;
	public final String label;

	private Deleted(String value, String label) {
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
