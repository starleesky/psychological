package cn.com.tsjx.common.util.logger;

import org.slf4j.Logger;

/**
 * @author GY
 * @Description: 调试输出日志
 * @date 2013-11-29 上午8:44:37
 */
public class LoggerDebugUtil {
	
	public static String str = "====================";
	
	public static void Info(Logger logger , String msg) {
		if(logger.isDebugEnabled()) {
			logger.info(str+msg+str);
		}
	}
	
	public static void Info(Logger logger , String format ,Object arg) {
		if(logger.isDebugEnabled()) {
			logger.info(str+format+str,arg);
		}
	}
	
	public static void Info(Logger logger , String format , Object arg1 , Object arg2) {
		if(logger.isDebugEnabled()) {
			logger.info(str+format+str , arg1 , arg2);
		}
	}
	
}
