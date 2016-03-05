package cn.com.tsjx.common.config.payment;

import cn.com.tsjx.common.web.interfaces.IInterfaces;


/**
 * 
 * 
 * 
 * xyl (2015-9-28 下午9:19:08)
 */
public class ALiPayConfig implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner;
	// 商户的密钥
	public static String key;
	// 商户的私钥
	public static String privateKey;
	// 商户的公钥
	public static String publicKey;
	// 调试用，创建TXT日志文件夹路径
	public static String logPath;
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String inputCharset;
	// 签名方式 支付
	public static String paySignType;
	// 签名方式 退款
	public static String refundSignType;
	public static String sellerEmail;
	
	private String _partner;
	private String _key;
	private String _privateKey;
	private String _publicKey;
	private String _logPath;
	private String _inputCharset;
	private String _paySignType;
	private String _refundSignType;
	private String _sellerEmail;
	
	/**
	 * 
	 * @param partner
	 * @param key
	 * @param privateKey
	 * @param publicKey
	 * @param logPath
	 * @param inputCharset
	 * @param paySignType
	 * @param refundSignType
	 * @param sellerEmail
	 */
	public ALiPayConfig(String partner, String key, String privateKey, String publicKey, String logPath, String inputCharset, String paySignType, String refundSignType, String sellerEmail) {
		super();
		
		ALiPayConfig.partner = this._partner = partner;
		ALiPayConfig.key = this._key = key;
		ALiPayConfig.privateKey = this._privateKey = privateKey;
		ALiPayConfig.publicKey = this._publicKey = publicKey;
		ALiPayConfig.logPath = this._logPath = logPath;
		ALiPayConfig.inputCharset = this._inputCharset = inputCharset;
		ALiPayConfig.paySignType = this._paySignType = paySignType;
		ALiPayConfig.refundSignType = this._refundSignType = refundSignType;
		ALiPayConfig.sellerEmail = this._sellerEmail = sellerEmail;
		
		// 可以在先后顺序不对时使用
//		callBack.callBack(this);
	}

	public String get_partner() {
		return _partner;
	}
	public String get_key() {
		return _key;
	}
	public String get_privateKey() {
		return _privateKey;
	}
	public String get_publicKey() {
		return _publicKey;
	}
	public String get_logPath() {
		return _logPath;
	}
	public String get_inputCharset() {
		return _inputCharset;
	}
	public String get_paySignType() {
		return _paySignType;
	}
	public String get_refundSignType() {
		return _refundSignType;
	}
	public String get_sellerEmail() {
		return _sellerEmail;
	}
	
	@SuppressWarnings("unused")
	private static IInterfaces.ICallBack callBack;
	
	public static void setCallBack(IInterfaces.ICallBack callBack) {
		ALiPayConfig.callBack = callBack;
	}
	
}
