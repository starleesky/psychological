package cn.com.tsjx.common.enums;
/**
 * @Type FlowSequence
 * @Desc 
 * @author hefan
 * @date 2015年7月16日
 * @Version V1.0
 */
public enum FlowSequence {

    LINE("SEQ_LINES_CODE", "线路编码序列"), 
    HOTEL("SEQ_HOTEL_CODE", "酒店编码序列"), 
    TICKET("SEQ_TICKET_CODE", "门票编码序列"), 
    KITCHEN("SEQ_KITCHEN_CODE", "后厨编码序列"), 
    LINEORDER("SEQ_LINEORDER_CODE", "线路订单编码序列"), 
    HOTELORDER("SEQ_HOTELORDER_CODE", "酒店订单编码序列"),
    TICKETORDER("SEQ_TICKETORDER_CODE", "门票订单编码序列"),
    CORP("SEQ_CORP_CODE", "企业编码序列"),
    SCENIC("SEQ_SCENIC_CODE", "景区编码序列"),
    TRADE("SEQ_TRADE_CODE", "流水号序列"),
    GROUP_INFO("SEQ_GROUP_INFO_CODE", "团购编码序列"),
    GOODS("SEQ_GOODS_CODE", "商品编码序列");
    
    public String value;
    public String label;

    private FlowSequence(String value, String label) {
        this.value = value;
        this.label = label;
    }
}
