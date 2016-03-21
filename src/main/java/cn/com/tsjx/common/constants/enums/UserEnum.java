package cn.com.tsjx.common.constants.enums;

public enum UserEnum {

	/*销售方式(类型)*/
	user_type_admin("0", "超级管理员", "user_type"),
	user_type_master("1", "管理员", "user_type"),
	user_type_member("2", "普通会员", "user_type"),
	user_type_company("3", "企业管理员", "user_type");

	private final String code;
	private final String description;
	private final String type;

	private UserEnum(String code, String description, String type) {
		this.code = code;
		this.description = description;
		this.type = type;
	}

	public String code() {
		return this.code;
	}

	public String description() {
		return this.description;
	}

	public static String getDiscribeByCode(String code) {
		String description = null;
		for (UserEnum ie : UserEnum.values()) {
			if (ie.code.equals(code)) {
				description = ie.description;
			}
		}
		return description;
	}

	public static UserEnum[] getEnumsByType(String type) {
		UserEnum[] enums = new UserEnum[] {};
		int i = 0;
		for (UserEnum ie : UserEnum.values()) {
			if (ie.type.equals(type)) {
				enums[i++] = ie;
			}
		}
		return enums;
	}

	public static void main(String[] args) {
		UserEnum[] enums = UserEnum.getEnumsByType("procedures");
		System.out.println("enums length is :" + enums.length);
	}

}
