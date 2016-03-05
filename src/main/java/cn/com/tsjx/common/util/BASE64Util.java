package cn.com.tsjx.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * @Type CodeUtil
 * @Desc 
 * @author hefan
 * @date 2015年9月24日
 * @Version V1.0
 */
public class BASE64Util {

    private static int random() {
        return (int) (100 * Math.random() + 1);
    }

    /**
     * 将字符串字节按位异或。
     * @param str 字符串
     * @param num 异或数
     * @return
     */
    private static String xor(String str, int num) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int charVal = str.charAt(i);
            charVal ^= num;
            result.append((char) charVal);
        }
        return result.toString();
    }

    /**
     * Base64加密
     * @param ticket
     * @return
     */
    public static String encode(String ticket) {
        ticket = StringUtils.reverse(Base64.encodeBase64String(ticket.getBytes()));
        Integer rand = random();
        // 按位异或
        ticket = xor(ticket, rand % 32);
        // rand encode
        String encodeRand = Base64.encodeBase64String(String.valueOf(rand).getBytes());
        // encode(encode(rand) + ';' + encode(ticket))
        String encodeTicket = Base64.encodeBase64String(ticket.getBytes());
        String encode = encodeRand + ";" + encodeTicket;
        return Base64.encodeBase64String(encode.getBytes());
    }

    /**
     * Base64解密
     * @param ticket
     * @return
     */
    public static String decode(String userKey) throws Exception {
        userKey = new String(Base64.decodeBase64(userKey));
        String[] strs = userKey.split(";");
        // 获取 rand
        int rand = Integer.parseInt(new String(Base64.decodeBase64(strs[0])));
           
        // 获取 encode
        String ticket = new String(Base64.decodeBase64(strs[1]));
        // 按位异或
        ticket = xor(ticket, rand % 32);
        return new String(Base64.decodeBase64(StringUtils.reverse(ticket)));
    }

    public static void main(String[] args) {
        System.out.println("E: " + encode("hefan"));

    }
}
