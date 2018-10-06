package cn.com.tsjx.common.config;

import org.springframework.beans.factory.InitializingBean;

/**
 * @Type InterfaceConfig
 * @Desc
 * @author hefan
 * @date 2015年8月31日
 * @Version V1.0
 */
public class InterfaceConfig implements InitializingBean {

    public static String URL;

    private String url;
  

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static String getURL() {
		return URL;
	}

	public static void setURL(String uRL) {
		URL = uRL;
	}

	@Override
    public void afterPropertiesSet() throws Exception {
        URL = this.getUrl().trim();
    }
}
