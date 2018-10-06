package cn.com.tsjx.common.enums;

/**
 * 支付方式
 * @Type PaywayEnum
 * @Desc 
 * @author tianzhonghong
 * @date 2015年9月10日
 * @Version V1.0
 */
public enum PaywayEnum {

    /**
     * 支付宝，值：1
     */
    Alipay("支付宝", 1L),

    /**
     * 微信公众号支付，值：2
     */
    Weixin("微信公众号支付", 2L),

    /**
     * 景区到付（仅景区)
     */
    ScenicPay("景区到付", 3L),

    /**
     * 酒店到付（仅酒店)
     */
    HotelPay("酒店到付", 4L),
    /**
     * 微信App支付，值：5
     */
    WeixinApp("微信App支付", 5L),
    
    /**
     * B2B余额支付，值6
     */
    B2BBalance("B2B余额支付",6L);
    
    public final String name;
    public final Long value;

    private PaywayEnum(String name, Long value) {
        this.name = name;
        this.value = value;
    }

    public Long getCode() {
    	return value;
    }
    public String getLabel() {
    	return name;
    }

    public String getName() {
        return name;
    }

    public Long getValue() {
        return value;
    }

    public static String valueOfCode(Long code) {
        for (PaywayEnum e : PaywayEnum.values()) {
            if (e.value.equals(code)) {
                return e.name;
            }
        }
        return null;
    }
}
