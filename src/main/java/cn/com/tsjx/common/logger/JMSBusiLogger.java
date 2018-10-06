//package cn.com.tsjx.common.logger;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * JMS日志记录器。
// * @author liwei
// */
//class JMSBusiLogger extends BusiLogger {
//
//	private String type;
//	private JMSMessageAdapter messageAdapter;
//
//	private JMSBusiLogger(String type, Configuration configuration) {
//		this.type = type;
//		this.messageAdapter = JMSMessageAdapter.getInstance(configuration);
//	}
//
//	@Override
//	protected void _log(String message) {
//		this.messageAdapter.sender(type, message);
//	}
//
//	private static final Map<String, JMSBusiLogger> loggerMap = new HashMap<String, JMSBusiLogger>();
//
//	synchronized static JMSBusiLogger getInstance(String type, Configuration configuration) {
//		JMSBusiLogger logger = loggerMap.get(type);
//		if (logger == null) {
//			logger = new JMSBusiLogger(type, configuration);
//			loggerMap.put(type, logger);
//		}
//		return logger;
//	}
//}