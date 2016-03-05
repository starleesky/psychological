package cn.com.tsjx.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务异常常量
 * 
 * @author crazy_cabbage
 * 
 */
public interface BizExceptionConstant {
	/**
	 * 成功
	 */
	int SUCCESS = 0;
	/**
	 * md5 操作异常
	 */
	int MD5_OPERATE_EXCEPTION = 1;
	/**
	 * des 操作异常
	 */
	int DES_OPERATE_EXCEPTION = 21;
	/**
	 * base64 操作异常
	 */
	int BASE64_OPERATE_EXCEPTION = 2;
	/**
	 * 编码不支持异常
	 */
	int UNSUPPORTED_ENCODING_EXCEPTION = 3;
	/**
	 * URL 连接异常
	 */
	int URL_CONNECTION_EXCEPTION = 4;

	/**
	 * 不支持拼音异常
	 */
	int UNSUPPORTED_PINYIN_EXCEPTION = 5;
	/**
	 * 业务错误
	 */
	int BIZ_ERROR = 6;
	/**
	 * 图片产生异常
	 */
	int PICTURE_GENERATOR_ERROR = 7;
	/**
	 * 短彩信发送错误
	 */
	int MSG_SEND_ERROR = 8;
	/**
	 * 模板合并错误
	 */
	int TEMPLATE_MERAGE_ERROR = 9;
	/**
	 * DOM4J 获取元素出错
	 */
	int DOM4J_VISTOR_ERROR = 10;
	/**
	 * DOM4J 解析报错
	 */
	int DOM4J_PARSE_ERROR = 10;
	/**
	 * ZIP压缩出错
	 */
	int ZIP_COMPRESS_ERROR = 11;
	/**
	 * 彩信号码出错
	 */
	int MMS_NO_ERROR = 11;
	/**
	 * 编码已被使用
	 */
	int CODE_ALREADY_USED = 12;
	/**
	 * 重复操作
	 */
	int REPETITIVE_OPERATE = 13;
	/**
	 * 支付宝配制异常
	 */
	int ALIPAY_CONFIG_ERROR = 14;
	/**
	 * 订单号为空错误
	 */
	int ORDER_NULL_ERROR = 15;
	/**
	 * 订单类型出错
	 */
	int ORDER_TYPE_ERROR = 16;
	/**
	 * 响应流被打判或低层SOCKET中断
	 */
	int RESPONSE_RESET = 17;
	/**
	 * 日期转换错误
	 */
	int DATE_PRASE_ERROR = 18;
	/**
	 * 系统选项没有配制错误
	 */
	int SYS_OPTION_NOT_CONFIG_ERROR=19;
	/**
	 * JAXB 解析错误
	 */
	int JAXB_ERROR=20;
	/**
	 * 乐观锁异常
	 */
    int OPTIMISTIC_EXCEPTION = 101;
	/**
	 * 低层数据库操作异常
	 */
	int DB_OPERATE_EXCEPTION = 102;
	/**
	 * 实体为空异常
	 */
	int ENTITY_NULL_EXCEPTION = 103;

	/**
	 * 没有主键可用
	 */
	int NO_ID_CAN_USED = 104;
	/**
	 * 结果集不唯一
	 */
	int RESULT_NOT_UNIQUE = 105;

	/**
	 * 请求参数错误
	 */
	int PARAMTER_ERROR = 1001;
	/**
	 * 用户名为空
	 */
	int USER_NAME_NULL = 1003;
	/**
	 * 密码为空
	 */
	int PASSWORD_NULL = 1004;
	/**
	 * 用户错误
	 */
	int USER_NAME_ERROR = 1006;
	/**
	 * 密码错误
	 */
	int PASSWORD_ERROR = 1007;
	/**
	 * 签名错误
	 */
	int SIGN_ERROR = 1008;
	/**
	 * 请求名错误
	 */
	int TRANSACTION_NAME_ERROR = 1009;
	/**
	 * 项目代码错误
	 */
	int APPLICATION_ERROR = 1010;
	/**
	 * XML格式错误
	 */
	int XML_FORMAT_ERROR = 1012;
	/**
	 * JSON格式错误
	 */
	int JSON_FORMAT_ERROR = 1013;
	/**
	 * 数据库异常
	 */
	int DB_ERROR = 1014;
	/**
	 * 其他错误
	 */
	int OTHER_ERROR = 1015;
	/**
	 * 报表编译错误
	 */
	int REPORT_COMPILE_ERROR=1016;
	/**
	 * 报表导出错误
	 */
	int REPORT_EXPORT_ERROR=1017;
	
	Map<Integer, String> INFO = new HashMap<Integer, String>(){/**
		 * 
		 */
		private static final long serialVersionUID = -78195273887910515L;

	{
		put(SUCCESS, "成功");
        put(MD5_OPERATE_EXCEPTION,"md5操作异常");
	    put(DES_OPERATE_EXCEPTION,"des操作异常");
	    put(BASE64_OPERATE_EXCEPTION,"base64操作异常");
	    put(UNSUPPORTED_ENCODING_EXCEPTION,"编码不支持异常");
	    put(URL_CONNECTION_EXCEPTION, "URL连接异常");
	    put(UNSUPPORTED_PINYIN_EXCEPTION, "不支持拼音异常");
	    put(BIZ_ERROR,"业务错误");
	    put(PICTURE_GENERATOR_ERROR,"图片产生异常");
	    put(MSG_SEND_ERROR, "短彩信发送错误");
        put(TEMPLATE_MERAGE_ERROR,"模板合并错误");
	    put(DOM4J_VISTOR_ERROR,"DOM4J 获取元素出错");
	    put(DOM4J_PARSE_ERROR,"DOM4J 解析报错");
	    put(ZIP_COMPRESS_ERROR,"ZIP压缩出错");
	    put(MMS_NO_ERROR, "彩信号码出错");
	    put(CODE_ALREADY_USED,"编码已被使用");
        put(REPETITIVE_OPERATE,"重复操作"); 
        put(ALIPAY_CONFIG_ERROR,"支付宝配制异常");
        put(ORDER_NULL_ERROR,"订单号为空错误");
        put(ORDER_TYPE_ERROR,"订单类型出错");
        put(RESPONSE_RESET,"响应流被打判或低层SOCKET中断");
	    put(DATE_PRASE_ERROR,"日期转换错误");
	    put(SYS_OPTION_NOT_CONFIG_ERROR,"系统选项没有配制错误");
	    put(JAXB_ERROR, "JAXB 解析错误");
	    put(OPTIMISTIC_EXCEPTION,"乐观锁异常");
	    put(DB_OPERATE_EXCEPTION,"低层数据库操作异常");
	    put(ENTITY_NULL_EXCEPTION,"实体为空异常");
	    put(NO_ID_CAN_USED,"没有主键可用");
	    put(RESULT_NOT_UNIQUE,"结果集不唯一");
        put(PARAMTER_ERROR,"请求参数错误");
        put(USER_NAME_NULL,"用户名为空");
	    put(PASSWORD_NULL,"密码为空");
	    put(USER_NAME_ERROR,"用户错误");
	    put(PASSWORD_ERROR,"密码错误");
	    put(SIGN_ERROR,"签名错误");
	    put(TRANSACTION_NAME_ERROR,"请求名错误");
	    put(APPLICATION_ERROR,"项目代码错误");
		put(XML_FORMAT_ERROR,"XML格式错误");
		put(JSON_FORMAT_ERROR,"JSON格式错误");
		put(DB_ERROR,"数据库异常");
	    put(OTHER_ERROR,"其他错误");
	    put(REPORT_COMPILE_ERROR,"报表编译错误");
	    put(REPORT_EXPORT_ERROR,"报表导出错误");
	}};

}
