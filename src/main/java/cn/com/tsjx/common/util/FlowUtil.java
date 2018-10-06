package cn.com.tsjx.common.util;

import java.util.Date;

import cn.com.tsjx.common.enums.FlowType;
import cn.com.tsjx.common.util.date.DateUtil;

public final class FlowUtil {
	
	/**
	 * 产品Code生成
	 * @param flowType
	 * @param no 唯一序列号
	 * @return
	 */
	public static String getProductCode(FlowType flowType, Long no) {
		return  flowType.prefix+DateUtil.format(new Date(), DateUtil.yyyyMMdd) + String.format("%1$06d", no);
	}
	

	/**
	 * 景区Code生成
	 * @param no 号码
	 * @return  编码
	 */
	public static String getScenicCode(Long no) {
		return  DateUtil.format(new Date(), DateUtil.yyyyMMdd) + String.format("%1$06d", no);
	}
	
	

	/**
	 * 订单编号生成器
	 * @param flowType
	 * @param no
	 * @return
	 */
	public static String getOrderCode(FlowType flowType, Long no) {
		return flowType.prefix+DateUtil.format(new Date(), DateUtil.yyyyMMdd) + String.format("%1$010d", no);
	}
	
	
    /**
     * 订单编号生成器
     * @param flowType
     * @param no
     * @return
     */
    public static String getOrderTradeCode(Long no) {
        return DateUtil.format(new Date(), DateUtil.yyyyMMdd) + String.format("%1$010d", no);
    }

    /**
     * 检票号产生器
     * @param checkCode 检票号
     * @return 检票号
     */
	public static String getCheckCode(Long checkCode) {
		return  "C"+DateUtil.format(new Date(), DateUtil.yyyyMMdd) + String.format("%1$09d", checkCode);
	}
	
	
	
	/**
	 * 辅助检票号产生器
	 * @param assistCheckCode 辅助检票号
	 * @return 辅助检票号
	 */
	public static String getAssistCheckCode(Long assistCheckCode) {
		return   String.format("%1$08d", assistCheckCode);
	}
	
	
	/**
     * 系统菜单编码
     * @param no 号码
     * @return  编码
     */
    public static String getMenuCode(String pre,Long no) {
        return  pre + String.format("%1$06d", no);
    }
    
    
    /**
     * 获取企业码
     * @param pre
     * @param no
     * @param pyCode
     * @return
     */
    public static String getCorpCode(String pre,Long no,String pyCode) {
        return  pre + String.format("%1$03d", no)+pyCode;
    }
    
}
