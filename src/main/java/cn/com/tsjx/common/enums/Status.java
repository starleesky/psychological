package cn.com.tsjx.common.enums;

/**
 * 状态枚举
 * @author cj001
 *
 */
public enum Status {


	ENABLE(0L,"启用"),

	DISABLE(1L,"禁用");
	
	private final Long value;
	private final String label;
	
	private Status(Long value, String label) {
		this.value = value;
		this.label = label;
	}
	public Long value() {
		return this.value;
	}

	public String label() {
		return this.label;
	}
}
