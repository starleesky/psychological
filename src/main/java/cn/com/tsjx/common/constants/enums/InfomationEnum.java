package cn.com.tsjx.common.constants.enums;

public enum InfomationEnum {

	/*销售方式(类型)*/
	sell_type_cs("0","出售","sell_type"),
	sell_type_zl("1","租赁","sell_type"),
	sell_type_qg("2","求购","sell_type"),
	sell_type_qz("3","求租","sell_type"),
	
	/*设备情况*/
	equipment_condition_xsb("0","新设备","equipment_condition"),
	equipment_condition_ers("1","二手设备","equipment_condition"),
	equipment_condition_zzz("2","再制造","equipment_condition"),
	
	/*手续资料*/
	procedures_qq("0","手续齐全","procedures"),
	procedures_wsx("1","无手续","procedures"),
	procedures_jk("2","有无手续均可","procedures"),
	
	/*设备来源（类型）*/
	src_gr("0","个人","src"),
	src_dw("1","单位","src"),
	src_dy("2","抵押","src"),
	src_fw("3","法务","src"),
	
	/*信息状态*/
	status_cg("0","草稿","status"),		
	status_sj("1","上架","status"),		
	status_ys("2","已售","status"),		
	status_xj("3","下架","status");		
	
	private final String code;
	private final String description;
	private final String type;
	
	private InfomationEnum(String code, String description,String type){
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
		for(InfomationEnum ie : InfomationEnum.values()) {
			if(ie.code.equals(code)) {
				description = ie.description;
			}
		}
		return description;
	}
	
	public static InfomationEnum[] getEnumsByType(String type) {
		InfomationEnum[] enums = new InfomationEnum[] {};
		int i = 0;
		for(InfomationEnum ie : InfomationEnum.values()) {
			if(ie.type.equals(type)) {
				enums[i++] = ie;
			}
		}
		return enums;
	}
	
	public static void main(String[] args) {
		InfomationEnum[] enums = InfomationEnum.getEnumsByType("procedures");
		System.out.println("enums length is :" + enums.length);
	}
	
	
	
}