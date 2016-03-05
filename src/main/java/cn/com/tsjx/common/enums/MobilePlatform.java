package cn.com.tsjx.common.enums;
/**
 * @Type MobilePlatform
 * @Desc 
 * @author hefan
 * @date 2015年9月25日
 * @Version V1.0
 */
public enum MobilePlatform {

    WEIXIN("weixin","微信"),
    ANDROID("android","安卓"),
    IOS("ios","苹果"),
    UNKNOWN("unknown","苹果");
    
    private final String value;
    private final String label;
    
    private MobilePlatform(String value, String label) {
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
