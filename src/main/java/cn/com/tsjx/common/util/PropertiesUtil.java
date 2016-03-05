package cn.com.tsjx.common.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesUtil {

	private String propertiesPath;
	
	private Properties props;
	
	public PropertiesUtil(String propertiesPath) {
		this.propertiesPath = propertiesPath;
		initProperties();
	}
	public String getPropertiesPath() {
		return propertiesPath;
	}

	public void setPropertiesPath(String propertiesPath) {
		this.propertiesPath = propertiesPath;
		initProperties();
	}

	public Properties getProps() {
		return props;
	}
	
	public String getValue(String key){
		return props.getProperty(key);
	}
	
	public String getValue(String key,String defaultVaue){
		return props.getProperty(key,defaultVaue);
	}
	
	public Object getValue(Object key){
		return props.get(key);
	}
	private void initProperties(){
		Resource resource = new ClassPathResource(propertiesPath);
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			props =new Properties();
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		PropertiesUtil pu =new PropertiesUtil("/config/memcached.properties");
		String client =pu.getValue("UserCache.client","10.132.25.241:11211");
		System.out.println(client);
		String str[] =client.split(":");
		System.out.println(str[0]);
		System.out.println(str[1]);
	}
}
