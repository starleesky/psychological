package cn.com.tsjx.common.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认的日志记录器，记录到Log4j。
 * @author liwei
 */
class DefaultBusiLogger extends BusiLogger {

	private Logger logger = LoggerFactory.getLogger("BUSI_LOGGER");

	private DefaultBusiLogger() {

	}

	public void _log(String message) {
		logger.info(message);
	}

	private static BusiLogger INSTANCE = new DefaultBusiLogger();

	static BusiLogger getInstance(Configuration configuration) {
		return INSTANCE;
	}
}