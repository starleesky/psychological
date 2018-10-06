package cn.com.tsjx.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.com.tsjx.common.exception.BusinessException;

/**
 * 日期工具类
 * 
 * @Type DateUtil
 * @Desc
 * @author hefan
 * @date 2015年5月1日
 * @Version V1.0
 */
public final class DateUtil {
    /**
     * 通用格式标识 yyyy-MM-dd HH:mm
     */
    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    /**
     * 通用格式标识 yyyy-MM-dd HH:mm:ss
     */
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    /**
     * 通用格式标识 yyyy-MM-dd HH
     */
    public static final String yyyy_MM_dd_HH = "yyyy-MM-dd HH";
    /**
     * 通用格式标识 yyyy/MM/dd
     */
    public static final String yyyy__MM__dd = "yyyy/MM/dd";
    /**
     * 通用格式标识 yyyy-MM-dd
     */
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    /**
     * 通用格式标识yyyyMMdd
     */
    public static final String yyyyMMdd = "yyyyMMdd";
    /**
     * 通用格式标识 HH:mm
     */
    public static final String HH_mm = "HH:mm";
    /**
     * 通用格式标识 HHmm
     */
    public static final String HHmm = "HHmm";
    /**
     * 通用格式标识ww
     */
    public static final String ww = "ww";
    /**
     * 通用格式标识w
     */
    public static final String w = "w";
    /**
     * 通用格式标识yyyy
     */
    public static final String yyyy = "yyyy";
    /**
     * 通用格式标识 yyyy年MM月
     */
    public static final String yyyy__MM__ = "yyyy年MM月";
    /**
     * 通用格式标识yyyy年ww周
     */
    public static final String yyyy__ww__ = "yyyy年ww周";

    /**
     * 把日期格式化字符串
     * 
     * @param date 要格式化的日期
     * @param pattern 日期的格式
     * @return 格式化后的字符串
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return StringUtil.EMPTY;
        }
        if (StringUtil.isBlank(pattern)) {
            pattern = yyyy_MM_dd_HH_mm;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 把日期字符串转换成日期对象
     * 
     * @param dateStr 日期字符串
     * @param pattern 格式串
     * @return 转换后的日期
     */
    public static Date parse(String dateStr, String pattern) {
        if (StringUtil.isBlank(dateStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new BusinessException("日期转换错误");
        }
    }

    /**
     * 去掉时分秒，毫秒的写法
     * 
     * @param date 日期
     * @return 去掉时分秒，毫秒的方法
     */
    public static Date shortDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 计算出两个日期间所有的 [1..7]一星期中周几 的日期
     * 
     * @param startTime 开始日期
     * @param endTime 结束日期
     * @param days 星期几的集合
     * @return
     * 
     *         日期从 周日开始算的 周日 1， 周一2， 周二3， 周三4， 周四5， 周五6， 周六7
     * 
     */
    public static List<Date> getComposeDates(Date startTime, Date endTime, List<Integer> days) {
        List<Date> list = new ArrayList<Date>();
        try {
            int i = 0;
            Date initDate = startTime;
            while (initDate.getTime() <= endTime.getTime()) {
                DateFormat df = new SimpleDateFormat(yyyy_MM_dd);
                Calendar c = Calendar.getInstance();
                c.setTime(df.parse(df.format(initDate)));
                Integer day = c.get(Calendar.DAY_OF_WEEK);
                if (days.contains(day)) {
                    list.add(initDate);
                }
                initDate = getRelateDay(startTime, ++i);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new BusinessException("计算时间段内周几的日期 错误");
        }
        return list;
    }

    /**
     * 获得根据旧的日期获得新的日期
     * 
     * @param date 旧的日期
     * @param field 变动的时间部分
     * @param amount 变动量
     * @return 新的时间
     */
    public static Date add(Date date, int field, int amount) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.add(field, amount);
        return c.getTime();
    }

    /**
     * 获得天数
     * 
     * @param date 日期
     * @return 获得天数
     */
    public static int date(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        return c.get(Calendar.DATE);
    }

    /**
     * 获得月份
     * 
     * @param date 日期
     * @return 获得天数
     */
    public static int month(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        return c.get(Calendar.MONTH);
    }

    /**
     * 获得年份
     * 
     * @param date 日期
     * @return 获得年份
     */
    public static int year(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        return c.get(Calendar.YEAR);
    }

    /**
     * 获得日期的最大天数
     * 
     * @param date 日期
     * @return 最大的天数
     */
    public static int maxDate(Date date) {
        Calendar c = Calendar.getInstance();
        if (date != null) {
            c.setTime(date);
        }
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DATE, 1);
        c.add(Calendar.DATE, -1);
        return c.get(Calendar.DATE);
    }

    /**
     * 将日期转换为同一天的最大值
     * 
     * @param date
     * @return
     */
    public static Date getMaxDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 计算日期中相对前几天或者后几天的日期
     * 
     * @param initialTime 当前日期
     * @param relateDay 往前几天 or 往后几天
     * @return
     */
    public static Date getRelateDay(Date initialTime, int relateDay) {
        java.util.Calendar cld = Calendar.getInstance();
        cld.setTime(initialTime);
        cld.add(Calendar.DATE, relateDay);
        return cld.getTime();
    }

    /**
     * 计算日期中，相对前几个小时或者后几个小时
     * 
     * @param initialTime
     * @param relateHour
     * @return
     *
     */
    public static Date getRelateHour(Date initialTime, int relateHour) {
        java.util.Calendar cld = Calendar.getInstance();
        cld.setTime(initialTime);
        cld.add(Calendar.HOUR, relateHour);
        return cld.getTime();
    }

    /**
     * 计算日期中，相对前几分钟或者后几分钟
     * 
     * @param initialTime
     * @param relateMinute
     * @return
     *
     */
    public static Date getRelateMinute(Date initialTime, int relateMinute) {
        java.util.Calendar cld = Calendar.getInstance();
        cld.setTime(initialTime);
        cld.add(Calendar.MINUTE, relateMinute);
        return cld.getTime();
    }

    /**
     * 获取日期相差天数
     * 
     * @param
     * @return 日期类型时间
     * @throws ParseException
     */
    public static Long getDiffDay(String beginDate, String endDate) {
        DateFormat formatter = new SimpleDateFormat(yyyy_MM_dd);
        Long checkday = 0l;
        // 开始结束相差天数
        try {
            checkday = (formatter.parse(endDate).getTime() - formatter.parse(beginDate).getTime())
                    / (1000 * 24 * 60 * 60);
        } catch (ParseException e) {
            e.printStackTrace();
            checkday = null;
            throw new BusinessException("获取日期相差天数 错误");
        }
        return checkday;
    }

}
