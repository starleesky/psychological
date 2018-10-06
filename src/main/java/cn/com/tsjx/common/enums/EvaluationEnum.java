package cn.com.tsjx.common.enums;

public enum EvaluationEnum {

    /**
     * 未评价，值：F
     */
    NO("F", "未评价"),

    /**
     * 已评价，值：T
     */
    YES("T", "已评价");

    public final String value;
    public final String label;
    
    private EvaluationEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }
    public String getValue() {
        return value;
    }
    public String getLabel() {
        return label;
    }
    
    
}
