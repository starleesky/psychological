package cn.com.tsjx.common.config;

/**
 * 配置对象。见config.properties配置文件。
 * @author liwei
 */
public class Config {

	// 系统编码
	private String systemCode;
	// 应用系统地址
	private String serverName;
	// 应用系统根目录
	private String rootPath;
	// CDN文件地址
	private String cdnPath;
	// Dojo文件CDN地址
	private String dojoPath;
	// 静态文件地址
	private String staticPath;
	// 是否使用登录验证码
	private boolean useValid = true;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getStaticPath() {
		return staticPath;
	}

	public void setStaticPath(String staticPath) {
		this.staticPath = staticPath;
	}

	public String getCdnPath() {
		return cdnPath;
	}

	public void setCdnPath(String cdnPath) {
		this.cdnPath = cdnPath;
	}

	public String getDojoPath() {
		return dojoPath;
	}

	public void setDojoPath(String dojoPath) {
		this.dojoPath = dojoPath;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public boolean isUseValid() {
		return useValid;
	}

	public void setUseValid(boolean useValid) {
		this.useValid = useValid;
	}
}
