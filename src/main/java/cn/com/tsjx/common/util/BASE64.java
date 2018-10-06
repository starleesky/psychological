package cn.com.tsjx.common.util;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;

import cn.com.tsjx.common.constants.BizExceptionConstant;
import cn.com.tsjx.common.constants.CharsetConstant;
import cn.com.tsjx.common.util.exception.BizException;
import cn.com.tsjx.common.util.lang.StringUtil;



/**
 * base64 算法
 * @author crazy_cabbage
 *
 */
public class BASE64 extends CharacterEncoder {
	   /** this class encodes three bytes per atom. */
    protected int bytesPerAtom() {
	return (3);
    }

    /** 
     * this class encodes 57 bytes per line. This results in a maximum
     * of 57/3 * 4 or 76 characters per output line. Not counting the
     * line termination.
     */
    protected int bytesPerLine() {
	return (57);
    }

    /** This array maps the characters to their 6 bit values */
    private final static char pem_array[] = {
	//       0   1   2   3   4   5   6   7
		'A','B','C','D','E','F','G','H', // 0
		'I','J','K','L','M','N','O','P', // 1
		'Q','R','S','T','U','V','W','X', // 2
		'Y','Z','a','b','c','d','e','f', // 3
		'g','h','i','j','k','l','m','n', // 4
		'o','p','q','r','s','t','u','v', // 5
		'w','x','y','z','0','1','2','3', // 6
		'4','5','6','7','8','9','+','/'  // 7
	};

    /** 
     * encodeAtom - Take three bytes of input and encode it as 4
     * printable characters. Note that if the length in len is less
     * than three is encodes either one or two '=' signs to indicate
     * padding characters.
     */
    protected void encodeAtom(OutputStream outStream, byte data[], int offset, int len) 
	throws IOException {
	byte a, b, c;

	if (len == 1) {
	    a = data[offset];
	    b = 0;
	    c = 0;
	    outStream.write(pem_array[(a >>> 2) & 0x3F]);
	    outStream.write(pem_array[((a << 4) & 0x30) + ((b >>> 4) & 0xf)]);
	    outStream.write('=');
	    outStream.write('=');
	} else if (len == 2) {
	    a = data[offset];
	    b = data[offset+1];
	    c = 0;
	    outStream.write(pem_array[(a >>> 2) & 0x3F]);
	    outStream.write(pem_array[((a << 4) & 0x30) + ((b >>> 4) & 0xf)]);
	    outStream.write(pem_array[((b << 2) & 0x3c) + ((c >>> 6) & 0x3)]);
	    outStream.write('=');
	} else {
	    a = data[offset];
	    b = data[offset+1];
	    c = data[offset+2];
	    outStream.write(pem_array[(a >>> 2) & 0x3F]);
	    outStream.write(pem_array[((a << 4) & 0x30) + ((b >>> 4) & 0xf)]);
	    outStream.write(pem_array[((b << 2) & 0x3c) + ((c >>> 6) & 0x3)]);
	    outStream.write(pem_array[c & 0x3F]);
	}
    }
	/**
	 * 解码
	 * @param str 字符串
	 * @return base64 解码后的字符串
	 */
	public static String decode(String str){
	  return   new String(Base64.decodeBase64(str));
	}
	
	/**
	 * jdk 标准的base64编码
	 * @param bytes 要编码的字节数组
	 * @return 编码后的字符串
	 */
	public static String encodeByte(byte[] bytes){
		return new BASE64().encode(bytes);
	}
	
	/**
	 * 解码
	 * @param str 要解码的字符串
	 * @param times 解码的次数
	 * @return 解码后字符串
	 */
	public static String decode(String str ,int times){
		if(times <1){
			throw new BizException(BizExceptionConstant.BASE64_OPERATE_EXCEPTION, StringUtil.EMPTY);
		}
		for(int i=0 ; i<times ;i++){
			str = decode(str);
		}
		return str;
	}
	/**
	 * 宽型BASE64 解码
	 * @param str 要解码的字符串
	 * @return 解码后的字符串
	 */
	public static String decodeW(String str){
		return decode(str, 2);
	}
	/**
	 * 编码
	 * @param str 字符串
	 * @return base64 编码后的字符串
	 */
	public static String encode(String str){
	  return  Base64.encodeBase64String(str.getBytes(Charset.forName(CharsetConstant.UTF_8)));	
	}
	
	

	/**
	 * 编码
	 * @param str 字符串
	 * @param times 编码的次数
	 * @return 编码后的字符串
	 */
	public static String encode(String str,int times){
		if(times <1){
			throw new BizException(BizExceptionConstant.BASE64_OPERATE_EXCEPTION, StringUtil.EMPTY);
		}
		for(int i=0 ; i<times ;i++){
			str = encode(str);
		}
		return str;
	}
	/**
	 * 宽型BASE 64 编码
	 * @param str 要编码的字符串
	 * @return 编码后的字符串
	 */
   public static String encodeW(String str){
	   return encode(str, 2);
   }
}
