/**
 * 
 */
package cn.com.tsjx.common.constants;

/**
 * 支付方式
 * @author biejunbo
 * @date 2014-6-20 
 *
 */
public class PayMethodConstants {
    /**
     * 支付方式：支付宝
     */
    public static final String PAY_METHOD_ALIPAY_ACCOUNT = "alipay";
    
    /**
     * 支付方式 银联
     */
    public static final String PAY_METHOD_UNIONPAY_ACCOUNT="unionpay";
    
    /**
     * 支付方式：第三方支付
     */
    public static final String PAY_METHOD_ONLINE_THRIDPAY = "thridpay";
    
    /**
     * 支付方式：备用金账户
     */
    public static final String PAY_METHOD_VIRTUAL_ACCNT = "vm";
    
    /**
     * 支付方式：智游宝现金账户
     */
    public static final String PAY_METHOD_ZYBCASH_ACCNT = "zyb";
    
    /**
     * 现场支付
     */
    public static final String PAY_METHOD_SPOT_ACCNT="spot";

    /**
     * 接口支付方式来源标志： 1  平台    2 根据企业查询支付
     */
    public static final int PAY_METHOD_COMFROM_PLAT = 1;
    public static final int PAY_METHOD_COMFROM_CORP = 2;

}
