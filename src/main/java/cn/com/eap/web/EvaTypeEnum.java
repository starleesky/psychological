package cn.com.eap.web;

/**
 * Created by xin.l on 2018/10/7.
 *
 * @author xin.l
 */
public enum EvaTypeEnum {

    MBTI("MBTI", "MBTI测评记录导出", "1"), OQ45("OQ45", "OQ45测评记录导出", "2"), SCL90("SCL90", "SCL-90测评记录导出", "3");

    private final String code;
    private final String description;
    private final String type;

    private EvaTypeEnum(String code, String description, String type) {
        this.code = code;
        this.description = description;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String code() {
        return this.code;
    }

    public String description() {
        return this.description;
    }

    public static String getDiscribeByCode(String code) {
        String description = null;
        for (EvaTypeEnum ie : EvaTypeEnum.values()) {
            if (ie.code.equals(code)) {
                description = ie.description;
            }
        }
        return description;
    }

    public static String getDiscribeByType(String type) {
        String description = null;
        for (EvaTypeEnum ie : EvaTypeEnum.values()) {
            if (ie.type.equals(type)) {
                description = ie.description;
            }
        }
        return description;
    }

    public static EvaTypeEnum[] EvaTypeEnum(String type) {
        EvaTypeEnum[] enums = new EvaTypeEnum[]{};
        int i = 0;
        for (EvaTypeEnum ie : EvaTypeEnum.values()) {
            if (ie.type.equals(type)) {
                enums[i++] = ie;
            }
        }
        return enums;
    }

}
