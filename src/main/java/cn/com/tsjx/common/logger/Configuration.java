package cn.com.tsjx.common.logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

/**
 * 日志输出器配置信息
 * @author liwei
 */
class Configuration {

	public static final String PROPS_SEND_TYPE = "busilog.type";
	public static final String PROPS_JMS_DESTINATION_NAME = "jms.destinationName";
	public static final String PROPS_JMS_SERVER_URL = "jms.serverUrl";
	public static final String PROPS_JMS_MAX_CONNECT_NUM = "jms.maxConnectNum";
	public static final String PROPS_JMS_SEND_THREAD_NUM = "jms.threadNum";
	public static final String PROPS_JMS_SEND_QUEUE_DEPTH = "jms.queueDepth";
	public static final String PROPS_JMS_MESSAGE_TYPE = "jms.messageType";

	private Properties properties;

	public Configuration() {
		this.properties = new Properties(this.getDefaultProps());
	}

	public Configuration(InputStream is) {
		this.properties = new Properties(this.getDefaultProps());
		try {
			this.properties.load(is);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private Properties getDefaultProps() {
		Properties properties = new Properties();
		try {
			properties.load(new ClassPathResource("busilog.default.properties", this.getClass()).getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	private String _get(String name, String defaultVal) {
		return this.properties.getProperty(name, defaultVal);
	}

	public SendType getSendType() {
		String sendType = this._get(PROPS_SEND_TYPE, "STDOUT").toUpperCase();
		return SendType.valueOf(sendType);
	}

	public String getServerUrl() {
		return this._get(PROPS_JMS_SERVER_URL, "");
	}

	public String getDestinationName() {
		return this._get(PROPS_JMS_DESTINATION_NAME, "");
	}

	public String getMessageType() {
		return this._get(PROPS_JMS_MESSAGE_TYPE, "topic");
	}

	public int getMaxConnectNum() {
		String maxConnectNum = this._get(PROPS_JMS_MAX_CONNECT_NUM, "1");
		return Integer.parseInt(maxConnectNum);
	}

	public int getSendThreadNum() {
		String threadNum = this._get(PROPS_JMS_SEND_THREAD_NUM, "1");
		return Integer.parseInt(threadNum);
	}

	public int getSendQueueDepth() {
		String queueDepth = this._get(PROPS_JMS_SEND_QUEUE_DEPTH, "500");
		return Integer.parseInt(queueDepth);
	}

	public static enum SendType {
		JMS, STDOUT;
	}
}
