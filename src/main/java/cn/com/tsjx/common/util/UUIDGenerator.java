package cn.com.tsjx.common.util;

import java.util.UUID;

/**
 * UUIDGenerator 工具类
 * @Type UUIDGenerator
 * @Desc 
 * @author hefan
 * @date 2015年5月1日
 * @Version V1.0
 */
public class UUIDGenerator {


	 public static String getUUID() {
	  UUID uuid = UUID.randomUUID();
	  String str = uuid.toString();
	  // 去掉"-"符号
	  String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
	  return temp;
	 }
	 
}
