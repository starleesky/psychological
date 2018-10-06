package cn.com.tsjx.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串长度的工具类
 * 
 * @Type StringLengUtil
 * @Desc
 * @author tianzhonghong
 * @date 2015年8月21日
 * @Version V1.0
 */
public final class StringLengUtil {

    private static final String CHINESE = "[\u0391-\uFFE5]";

    /**
     * 判断字符串的长度,中文算2个长度
     *
     * @param strstr
     * @return
     */
    public static boolean isLengthStr(String strstr, int maxStrLength) {

        if (getStrWidth(strstr) > maxStrLength) {
            return true;
        }
        return false;
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     *
     * @param value 指定的字符串
     * @return 字符串的宽度
     */
    public static int getStrWidth(String value) {

        int valueLength = 0;

        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(CHINESE)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     *
     * 按照最长宽度截取字符串
     *
     * @param value
     * @param maxStrLength
     * @return
     */
    public static String cutStr(String value, int maxStrWidth) {

        if (StringUtils.isBlank(value)) {
            return null;
        }

        /* 需要保留的字符串长度 */
        int lengthToReserve = 0;
        /* 字符串宽度 */
        int width = 0;
        int stringLen = value.length();
        /* 获取字段值的宽度，如果含中文字符，则每个ascii字符宽度为1，否则为2 */
        for (int i = 0; i < stringLen; i++) {
            /* 获取一个字符 */
            char temp = value.charAt(i);
            /* 判断是否为ascii字符 */
            if (isAscii(temp)) {
                /* ascii字符宽度为1 */
                width += 1;
            } else {
                /* 其他文字符宽度为2 */
                width += 2;
            }
            /* 如果宽度大于最大宽度，直接跳出 */
            if (width > maxStrWidth) {
                break;
            }
            /* 需要保留的字符串长度加1 */
            lengthToReserve += 1;
        }
        String cut = value.substring(0, lengthToReserve);
        if (width > maxStrWidth) {
            cut += "...";
        }
        return cut;
    }

    /**
     *
     * 是否是ascii字符串
     *
     * @param ch
     * @return
     */
    static boolean isAscii(char ch) {
        if (ch >= 127 || ch < 0)
            return false;
        return true;
    }
}
