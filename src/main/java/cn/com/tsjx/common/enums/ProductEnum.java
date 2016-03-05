package cn.com.tsjx.common.enums;

/**
 * @Type ProductEnum
 * @Desc
 * @author hefan
 * @date 2015年7月26日
 * @Version V1.0
 */
public enum ProductEnum {

	TICKET("T", "门票"), HOTEL("H", "酒店"), LINES("L", "线路"), KITCHEN("K", "中央后厨"), GROUP(
			"G", "团购"), B2BLINES("B2BL", "B2B线路"), B2BTICKET("B2BT", "B2B门票");

	private final String value;
	private final String label;

	private ProductEnum(String value, String label) {
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
