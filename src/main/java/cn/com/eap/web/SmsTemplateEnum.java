package cn.com.eap.web;

/**
 * Created by xin.l on 2018/10/6.
 *
 * @author xin.l
 */
public enum SmsTemplateEnum {

    SMS_VERIFICATION("SMS_145920671", "您的验证码${code}，该验证码5分钟内有效，请勿泄漏于他人！", ""),
    SMS_NOTIFY_USER("SMS_145920822", "您已预约成功，我们会尽快与您联系并安排咨询，感谢支持！", ""),
    SMS_NOTIFY_MANAGERS("SMS_145920835", "平台有一条新预约通知，请及时查看。 预约人：${name} 所在单位：${unit} 手机号：${mobile_number}", ""),
    ;

    private final String code;
    private final String description;
    private final String type;

    private SmsTemplateEnum(String code, String description, String type) {
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
        for (SmsTemplateEnum ie : SmsTemplateEnum.values()) {
            if (ie.code.equals(code)) {
                description = ie.description;
            }
        }
        return description;
    }

    public static SmsTemplateEnum[] getEnumsByType(String type) {
        SmsTemplateEnum[] enums = new SmsTemplateEnum[]{};
        int i = 0;
        for (SmsTemplateEnum ie : SmsTemplateEnum.values()) {
            if (ie.type.equals(type)) {
                enums[i++] = ie;
            }
        }
        return enums;
    }
}
