package cn.com.tsjx.common.logger;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import cn.com.tsjx.common.logger.Configuration.SendType;
import cn.com.tsjx.common.util.json.JsonMapper;

/**
 * 业务日志记录器
 * @author liwei
 */
public abstract class BusiLogger {

	private static Configuration configuration;

	static {
		Resource resource = new ClassPathResource("/busilog.properties");
		if (!resource.exists()) {
			resource = new ByteArrayResource(new byte[0]);
		}
		try {
			configuration = new Configuration(resource.getInputStream());
		} catch (Exception e) {
			configuration = new Configuration();
			System.out.println("BUSI LOGGER ERROR: INIT ERROR.");
		}
	}

	/**
	 * 记录日志
	 * @param message 日志内容
	 * @param args 替换参数
	 */
	public void log(String message, Object... args) {
		FormattingTuple ft = MessageFormatter.arrayFormat(message, args);
		this._log(ft.getMessage());
	}

	public void jsonlog(Object object) {
		String message = JsonMapper.getMapper().toJson(object);
		this._log(message);
	}

	/**
	 * 日志输出
	 * @param message
	 */
	protected abstract void _log(String message);

	public static final BusiLogger getLogger(String type) {
		if (configuration.getSendType() == SendType.JMS) {
			return JMSBusiLogger.getInstance(type, configuration);
		}
		return DefaultBusiLogger.getInstance(configuration);
	}
}
