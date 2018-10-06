//package cn.com.tsjx.common.constants;
//
///**
// * 系统常量
// * 
// * @author crazy_cabbage
// * 
// */
//public interface SysOptionConstant {
//	/**
//	 * 景区票设置最大日期
//	 */
//	String SCENIC_TICKET_MONTH = "scenic_ticket_month";
//	/**
//	 * 套票设置最大日期
//	 */
//	String FAMILY_TICKET_MONTH = "family_ticket_month";
//	/**
//	 * 酒店房态最大日期
//	 */
//	String HOTEL_ROOM_STATUS_MONTH = "hotel_room_status_month";
//	/**
//	 * 景区门票订购最大数量
//	 */
//	String SCENIC_TICKET_MAX_BOOK_NUM = "scenic_ticket_max_book_num";
//	/**
//	 * 景区门票订购最小数量
//	 */
//	String SCENIC_TICKET_MIN_BOOK_NUM = "scenic_ticket_min_book_num";
//	/**
//	 * 餐饮票设置最大日期
//	 */
//	String REPAST_TICKET_MONTH = "repast_ticket_month";
//	/**
//	 * 提早预订天数
//	 */
//	String EARLY_BOOK_DAY = "early_book_day";
//	/**
//	 * 提早预订时间
//	 */
//	String EARLY_BOOK_TIME = "early_book_time";
//	/**
//	 * 企业可建用户人数
//	 */
//	String USER_NUM = "user_num";
//	/**
//	 * 销售佣金码设置最大日期
//	 */
//	String SALE_COMMISSION_MONTH = "sale_commission_month";
//	/**
//	 * 检票通知
//	 */
//	String CHECK_TICKET_NOTICE = "check_ticket_notice";
//	/**
//	 * 检票通知URL
//	 */
//	String CHECK_TICKET_NOTICE_URL = "check_ticket_notice_url";
//	/**
//	 * 检票通知前缀
//	 */
//	String CHECK_TICKET_NOTICE_ = "check_ticket_notice_";
//	/**
//	 * 最大通知次数
//	 */
//	String MAX_NOTICE_TIMES = "max_notice_times";
//	/**
//	 * 订单支付通知
//	 */
//	String ORDER_PAY_NOTICE_ = "order_pay_notice_";
//	/**
//	 * 订单支付最大通知次数
//	 */
//	String MAX_ORDER_PAY_NOTICE_TIMES = "max_order_pay_notice_times";
//	/**
//	 * 发码发短信
//	 */
//	String SEND_CODE_SEND_SM = "send_code_send_sm";
//	/**
//	 * 发码发彩信
//	 */
//	String SEND_CODE_SEND_MMS = "send_code_send_mms";
//	/**
//	 * 短信模板
//	 */
//	String SM_TEMPLATE = "sm_template";
//	/**
//	 * 演出短信模板
//	 */
//	String SHOW_SM_TEMPLATE = "show_sm_template";
//	/**
//	 * 短信模板
//	 */
//	String EXAMPLE_MSGTEMPLATE = "example_msgTemplate";
//	/**
//	 * 彩信模板
//	 */
//	String MMS_TEMPLATE = "mms_template";
//	/**
//	 * 演出彩信模板
//	 */
//	String SHOW_MMS_TEMPLATE = "show_mms_template";
//	/**
//	 * 彩信标题
//	 */
//	String MMS_TITLE = "mms_title";
//	/**
//	 * 发码类型
//	 */
//	String SEND_NO_TYPE = "send_no_type";
//	/**
//	 * 销售码最大设置月份
//	 */
//	String SALE_CODE_MAX_DAY = "sale_code_max_day";
//	/**
//	 * 支付订单参数名
//	 */
//	String ALIPAY_ORDER_PARAM_NAME = "alipay_order_param_name";
//	/**
//	 * 检票通知参数名
//	 */
//	String CHECK_NOTICE_PARAM_NAME = "check_notice_param_name";
//	/**
//	 * 硬件打印模板
//	 */
//	String HARDWARE_PRINT_TEMPLATE = "hardware_print_template";
//	/**
//	 * 硬件显示模板
//	 */
//	String HARDWARE_SHOW_TEMPLATE = "hardware_show_template";
//	/**
//	 * 硬件打印手机号码掩码
//	 */
//	String HARDWARE_PRINT_MOBILE_MARK = "hardware_print_mobile_mark";
//	/**
//	 * 淘宝同步标识
//	 */
//	String TAOBAO_SYNC = "taobao_sync";
//	/**
//	 * 淘宝订单身份证号截取
//	 */
//	String CLIP_TAOBAO_TRADE_IDNO = "clip_taobao_trade_idno";
//	/**
//	 * 短信中二维码下载地址
//	 */
//	String SM_GM_CHECKNO_URL = "sm_gm_checkno_url";
//	/**
//	 * 下单支付现场支付模式
//	 */
//	String ORDER_BOOK_PAY_SPOT = "order_book_pay_spot";
//	/**
//	 * 云闸机版本号
//	 */
//	String CLOUD_APP_VERSION = "CLOUD_APP_VERSION";
//	/**
//	 * 购买限定规则
//	 */
//	String PURCHASE_LIMIT_RULES = "purchase_limit_rules";
//	/**
//	 * 消息发送自定义
//	 */
//	String MSG_TEMPLATE_CUSTOM_SEND = "msg_template_custom_send";
//	/**是否使用结算中心**/
//	String ORDER_PAY_NEW_VM = "order_pay_new_vm";
//	/**
//	 * 自定义检票号
//	 */
//	String CUSTOM_CHECK_NO = "custom_check_no";
//	
//	/**
//	 * 供应商短信设置
//	 */
//	String SM_SET = "sm_set";
//
//	/**
//	 * 供应商彩信设置
//	 */
//	String MM_SET = "mm_set";
//	
//	/**
//	 * 下单支付智游宝账户支付模式
//	 */
//	String ORDER_PAY_ZHIYOUBAO = "order_pay_zhiyoubao";
//
//	/**
//	 * 绑定身份证
//	 */
//	String LOCKING_ID_CARD = "locking_id_card";
//
//	/**
//	 * 下单支付支付宝账户支付模式
//	 */
//	String ORDER_PAY_ZHIFUBAO = "order_pay_zhifubao";
//	
//	String CLOSE_DAYS ="close_days";
//	
//	/**短彩信切换**/
//	String SWITCH_SM_OPERATORS="switchSmOperators";
//}
