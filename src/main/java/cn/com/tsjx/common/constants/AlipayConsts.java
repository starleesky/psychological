package cn.com.tsjx.common.constants;

/**
 * 常量类
 * @author liwei
 */
public class AlipayConsts {

	/** 支付宝交易类型：即时到账交易接口(create_direct_pay_by_user) */
	public static final String SERVICE_DIRECT_PAY = "create_direct_pay_by_user";

	/** 支付宝交易类型：批量付款到支付宝账户接口（有密）(batch_trans_notify) */
	public static final String SERVICE_BATCH_TRANS = "batch_trans_notify";

	/** 支付宝交易类型：批量付款到支付宝账户接口（无密）(batch_trans_notify_no_pwd) */
	public static final String SERVICE_BATCH_TRANS_NOPASS = "batch_trans_notify_no_pwd";

	/** 支付宝交易类型：统一下单并支付接口(alipay.acquire.createandpay) */
	public static final String SERVICE_ACQUIRE_CREATEANDPAY = "alipay.acquire.createandpay";

	/** 支付宝交易类型：收单查询接口(alipay.acquire.query) */
	public static final String SERVICE_ACQUIRE_QUERY = "alipay.acquire.query";

	/** 支付宝交易类型：收单退款接口(alipay.acquire.refund) */
	public static final String SERVICE_ACQUIRE_REFUND = "alipay.acquire.refund";

	/** 支付宝交易类型：通知验证接口(notify_verify) */
	public static final String SERVICE_NOTIFY_VERIFY = "notify_verify";

	/** 支付宝交易类型：时间验证接口(query_timestamp) */
	public static final String SERVICE_QUERY_TIMESTAMP = "query_timestamp";

	/**
	 * 支付类型
	 */
	public static final String PAYMENT_TYPE = "1";
}
