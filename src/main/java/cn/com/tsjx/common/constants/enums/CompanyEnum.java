package cn.com.tsjx.common.constants.enums;

public enum CompanyEnum {

//	0未认证 1认证成功 2认证失败|
	/*销售方式(类型)*/
	status_audit("0","待审核","status"),
	status_success("1","认证成功","status"),
	status_failure ("2","认证失败","status"),

	deleted_t("T","删除","deleted"),
	delete_f("F","未删除","deleted")
	;

	private final String code;
	private final String description;
	private final String type;

	private CompanyEnum(String code, String description,String type){
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
		for(CompanyEnum ie : CompanyEnum.values()) {
			if(ie.code.equals(code)) {
				description = ie.description;
			}
		}
		return description;
	}
	
	public static CompanyEnum[] getEnumsByType(String type) {
		CompanyEnum[] enums = new CompanyEnum[] {};
		int i = 0;
		for(CompanyEnum ie : CompanyEnum.values()) {
			if(ie.type.equals(type)) {
				enums[i++] = ie;
			}
		}
		return enums;
	}
	
	public static void main(String[] args) {
		CompanyEnum[] enums = CompanyEnum.getEnumsByType("procedures");
		System.out.println("enums length is :" + enums.length);
	}
	
	
	
}
