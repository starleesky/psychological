package cn.com.tsjx.common.util.lang;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 金额转换工具类。
 * @author liwei
 */
public class MoneyUtils {

	/**
	 * 将分转换为圆。
	 * @param money
	 * @return
	 */
	public static double fenToYuan(long money) {
		BigDecimal decimal = new BigDecimal(money);
		decimal = decimal.divide(new BigDecimal(100));
		decimal = decimal.setScale(2, RoundingMode.HALF_EVEN);
		return decimal.doubleValue();
	}

	/**
	 * 将圆转换为分。
	 * @param money
	 * @return
	 */
	public static long yuanToFen(double money) {
		BigDecimal decimal = new BigDecimal(money);
		decimal = decimal.multiply(new BigDecimal(100));
		decimal = decimal.setScale(0, RoundingMode.HALF_EVEN);
		return decimal.longValue();
	}

	/**
	 * 格式化金额，格式：0.00
	 * @param money 金额值
	 * @return
	 */
	public static String format(double money) {
		DecimalFormat format = new DecimalFormat("0.00");
		return format.format(money);
	}
	
	/**
     * <p>
     * 两数相乘 结果是Long类型的.精确到分.
     * </p>
     * <ul>
     * 例子：商品的总价格:Long tallAmont=10234;需要转成以元为单位的.</li>
     * <li>使用BigDecimal来转化,BigDecimal tallAmontToB = new BigDecimal(tallAmont).divide(new BigDecimal(1000))</li>
     * <ul>
     * 
     * @param one 乘数 最后精确到分 <font color="red">单位是元</font>例如：10.23元.
     * @param two 被乘数 必须是小数. 例如：0.25
     * @return 相乘后转换成long 精确到2位 
     */
    public static Long multiplyToLong(BigDecimal one, BigDecimal two) {
        if (null == one || null == two) {
            return null;
        }
        BigDecimal result = one.multiply(two).setScale(2, BigDecimal.ROUND_FLOOR);

        return result.multiply(new BigDecimal(1000)).longValueExact();
    }

    /**
     * 两数相除 结果是Long类型的.精确到分.
     * 
     * @param one 除数 以元为单位
     * @param two 被除数
     * @return 相除后转换成long
     */
    public static Long divideToLong(BigDecimal one, BigDecimal two) {
        if (null == one || null == two) {
            return null;
        }
        return one.divide(two, 2, BigDecimal.ROUND_FLOOR).multiply(new BigDecimal(1000)).longValueExact();

    }

    /**
     * 两数相加 结果是Long类型的,精确到分.
     * 
     * @param one 以元为单位
     * @param two 以元为单位
     * @return 相加后转换成long
     */
    public static Long addToLong(BigDecimal one, BigDecimal two) {
        if (null == one || null == two) {
            return null;
        }
        BigDecimal result = one.add(two).setScale(2, BigDecimal.ROUND_FLOOR);
        return result.multiply(new BigDecimal(1000)).longValueExact();
    }

    /**
     * 两数相减 结果是Long类型的,精确到分.
     * 
     * @param one 以元为单位
     * @param two 以元为单位
     * @return 相减后转换成long
     */
    public static Long subtractToLong(BigDecimal one, BigDecimal two) {
        if (null == one || null == two) {
            return null;
        }
        if (one.compareTo(two) < 0) {
            return null;
        }
        BigDecimal result = one.subtract(two).setScale(2, BigDecimal.ROUND_FLOOR);
        return result.multiply(new BigDecimal(1000)).longValueExact();
    }

    /**
     * 对long型数据进行四舍五入
     * @param amount 需要处理的金额
     * @return 四舍五入后的金额
     */
    public static Long LongRound(Long amount) {
        if(null == amount)
        {
            return 0l;
        }
        long tailNum = amount % 10;
        long result = 0l;
        if (tailNum >= 5) {
            long add = 10L - tailNum;
            result = amount + add;
        } else {
            result = amount - tailNum;
        }
        return result;
    }
    
    
    
    /**
     * <p>
     * 两数相乘 结果是Double类型的.精确到分.
     * </p>
     * <ul>
     * 例子：商品的总价格:Double tallAmont=10234;需要转成以元为单位的.</li>
     * <li>使用BigDecimal来转化,BigDecimal tallAmontToB = new BigDecimal(tallAmont).divide(new BigDecimal(1000))</li>
     * <ul>
     * 
     * @param one 乘数 最后精确到分 <font color="red">单位是元</font>例如：10.23元.
     * @param two 被乘数 必须是小数. 例如：0.25
     * @return 相乘后转换成Double 精确到2位 
     */
    public static Double multiplyToDouble(BigDecimal one, BigDecimal two) {
        if (null == one || null == two) {
            return null;
        }
        BigDecimal result = one.multiply(two).setScale(2, BigDecimal.ROUND_FLOOR);

        return result.doubleValue();
    }

    /**
     * 两数相除 结果是Double类型的.精确到分.
     * 
     * @param one 除数 以元为单位
     * @param two 被除数
     * @return 相除后转换成Double
     */
    public static Double divideToDouble(BigDecimal one, BigDecimal two) {
        if (null == one || null == two) {
            return null;
        }
        return one.divide(two, 2, BigDecimal.ROUND_FLOOR).doubleValue();

    }

    /**
     * 两数相加 结果是Double类型的,精确到分.
     * 
     * @param one 以元为单位
     * @param two 以元为单位
     * @return 相加后转换成Double
     */
    public static Double addToDouble(BigDecimal one, BigDecimal two) {
        if (null == one || null == two) {
            return null;
        }
        BigDecimal result = one.add(two).setScale(2, BigDecimal.ROUND_HALF_DOWN);;
        return result.doubleValue();
    }

    /**
     * 两数相减 结果是Double类型的,精确到分.
     * 
     * @param one 以元为单位
     * @param two 以元为单位
     * @return 相减后转换成Double
     */
    public static Double subtractToDouble(BigDecimal one, BigDecimal two) {
        if (null == one || null == two) {
            return null;
        }
        if (one.compareTo(two) < 0) {
            return null;
        }
        BigDecimal result = one.subtract(two);
        return result.doubleValue();
    }
    
    public static void main(String[] args) {
        System.out.println(MoneyUtils.addToDouble(new BigDecimal(0.26), new BigDecimal(0.24)));
        System.out.println(MoneyUtils.subtractToDouble(new BigDecimal(0.95), new BigDecimal(0.22)));
        System.out.println(MoneyUtils.divideToDouble(new BigDecimal(0.55), new BigDecimal(0.1)));
        System.out.println(MoneyUtils.multiplyToDouble(new BigDecimal(0.21), new BigDecimal(0.25)));
    }
}
