package cn.com.tsjx.common.config.payment;

import org.springframework.beans.factory.InitializingBean;

import cn.com.tsjx.common.web.interfaces.IInterfaces;

/**
 * 
 * 
 * 
 * xyl (2015-9-29 下午6:04:23)
 */
public class WeiXinPayConfig implements InitializingBean, java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String APP_ID;
	public static String APP_SECRET;
	public static String MCH_ID;
	public static String API_KEY;
	public static String CERT_FILE_PATH;

	public static String APP_APP_ID;
	public static String APP_APP_SECRET;
	public static String APP_MCH_ID;
	public static String APP_API_KEY;
	public static String APP_CERT_FILE_PATH;

	private String appId;
	private String appSecret;
	private String mchId;
	private String apiKey;
	private String certFilePath;

	private String appAppId;
	private String appAppSecret;
	private String appMchId;
	private String appApiKey;
	private String appCertFilePath;

	public String getAppId() { return appId; }
	public void setAppId(String appId) { this.appId = appId; }
	public String getAppSecret() { return appSecret; }
	public void setAppSecret(String appSecret) { this.appSecret = appSecret; }
	public String getMchId() { return mchId; }
	public void setMchId(String mchId) { this.mchId = mchId; }
	public String getApiKey() { return apiKey; }
	public void setApiKey(String apiKey) { this.apiKey = apiKey; }
	public String getCertFilePath() { return certFilePath; }
	public void setCertFilePath(String certFilePath) { this.certFilePath = certFilePath; }
	public String getAppAppId() { return appAppId; }
	public void setAppAppId(String appAppId) { this.appAppId = appAppId; }
	public String getAppAppSecret() { return appAppSecret; }
	public void setAppAppSecret(String appAppSecret) { this.appAppSecret = appAppSecret; }
	public String getAppMchId() { return appMchId; }
	public void setAppMchId(String appMchId) { this.appMchId = appMchId; }
	public String getAppApiKey() { return appApiKey; }
	public void setAppApiKey(String appApiKey) { this.appApiKey = appApiKey; }
	public String getAppCertFilePath() { return appCertFilePath; }
	public void setAppCertFilePath(String appCertFilePath) { this.appCertFilePath = appCertFilePath; }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		APP_ID = this.getAppId().trim();
		APP_SECRET = this.getAppSecret().trim();
		MCH_ID = this.getMchId().trim();
		API_KEY = this.getApiKey().trim();
		CERT_FILE_PATH = this.getCertFilePath().trim();

		APP_APP_ID = this.getAppAppId().trim();
		APP_APP_SECRET = this.getAppAppSecret().trim();
		APP_MCH_ID = this.getAppMchId().trim();
		APP_API_KEY = this.getAppApiKey().trim();
		APP_CERT_FILE_PATH = this.getAppCertFilePath().trim();
		
		// 可以在先后顺序不对时使用
//		callBack.callBack(this);

		initialize();
	}

	private void initialize() {
	}
	
	@SuppressWarnings("unused")
	private static IInterfaces.ICallBack callBack;
	
	public static void setCallBack(IInterfaces.ICallBack callBack) {
		WeiXinPayConfig.callBack = callBack;
	}

}
