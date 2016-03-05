package cn.com.tsjx.common.constants;

/**
 * 公用常量
 * 
 * @author crazy_cabbage
 * 
 */
public interface CommonConstant {
	/**
	 * 用户权限CODE
	 */
	String USER_AUTH_CODE = "USER_AUTH_CODE";
	/**
	 * 用户验证码
	 */
	String USER_CHECK_CODE = "USER_CHECK_CODE";
	/**
	 * 用户ID
	 */
	String USER_ID = "USER_ID";
	/**
	 * 用户登录名
	 */
	String USER_CODE = "USER_CODE";
	/**
	 * 企业编码
	 */
	String CORP_CODE = "CORP_CODE";
	/**
	 * 企业主键
	 */
	String CORP_ID="CORP_ID";
	/**
	 * 企业集团编码
	 */
	String CORP_GROUP_CODE = "CORP_GROUP_CODE";
	/**
	 * 企业集团主键
	 */
	String CORP_GROUP_ID="CORP_GROUP_ID";
	/**
	 * 角色ID
	 */
	String ROLE_ID = "ROLE_ID";

	/**
	 * 权限码格式
	 */
	String AUTH_CODE_FORMAT = "#%1$s#";
	/**
	 * 偏移量字符串
	 */
	String OFFSET = "offset";
	/**
	 * PASSWORD
	 */
	String PASSWORD = "ff550ea5e6cee5dc4b1bf238563c5ed2";
	/**
	 * SOAP_CONTENT_TYPE
	 */
	String SOAP_CONTENT_TYPE = "application/soap+xml; charset=utf-8";
	/**
	 * POST方法
	 */
	String POST="POST";
	/**
	 * GET方法
	 */
	String GET="GET";
	/**
	 * 深大编码
	 */
	String SENDINFO="SENDINFO";
	/**
	 * 深大分销编码
	 */
	String SENDINFOFX="SENDINFOFX";
	/**
	 * 深大供应编码
	 */
	String SENDINFOGY="SENDINFOGY";
	/**
	 * 企业主键
	 */
	Long CORP_ID_VAL=1L;
	/**
	 * 企业集团主键
	 */
	Long CORP_GROUP_ID_VAL=1L;
	/**
	 * 系统用户
	 */
	String SYS_USER="SYSTEM";
	/**
	 * 文件下载描述
	 */
	String CONTENT_DISPOSITION="Content-Disposition";
	/**
	 * 在线下载
	 */
	String ATTACHMENT ="attachment;filename=\"%1$s\"";
	/**
	 * 在线打开
	 */
	String INLINE ="inline;filename=\"%1$s\"";
	/**
	 * 杂注
	 */
	String PRAGMA="Pragma";
	/**
	 * 缓存控制
	 */
	String CACHE_CONTROL="Cache-Control";
	/**
	 * 不缓存
	 */
	String  NO_CACHE="no-cache";
	/**
	 * MD5 
	 */
	String MD5="MD5";
	/**
	 * DES 算法名称
	 */
	String DES="DES";
	/**
	 *算法名称/加密模式/填充方式
	 *DES共有四种工作模式->>ECB:电子密码本模式、 CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
	 */
	String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
	
	/***最大通知次数**/
	Integer NoticeTimes = 10;
}
