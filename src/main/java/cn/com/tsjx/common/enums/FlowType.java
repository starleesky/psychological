package cn.com.tsjx.common.enums;

/**
 * 流水号类型
 * 
 * @Type FlowType
 * @Desc
 * @author tianzhonghong
 * @date 2015年6月30日
 * @Version V1.0
 */
public enum FlowType {
    LINE("L", "线路"), HOTEL("H", "酒店"), TICKET("T", "门票"), KITCHEN("K", "后厨"),LINEORDER("LO", "线路订单"), HOTELORDER("HO", "酒店订单"), TICKETORDER(
            "TO", "门票订单"),GROUP_INFO("G","团购"),GOODS("GD","商品");
    public String prefix;
    public String name;

    private FlowType(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }
}