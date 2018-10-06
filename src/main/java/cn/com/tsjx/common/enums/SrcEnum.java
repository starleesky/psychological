package cn.com.tsjx.common.enums;
/**
 * 
 * @Type SrcEnum
 * @Desc 订单下单来源 
 * @author lxh
 * @date 2015年12月3日
 * @Version V1.0
 */
public enum SrcEnum {
    
    MOBILE(1L,"移动端"),
    PC(2L,"PC端");
    
    public final Long value;
    public final String label;
    public Long getValue() {
        return value;
    }
    public String getLabel() {
        return label;
    }
    private SrcEnum(Long value, String label) {
        this.value = value;
        this.label = label;
    }
}
