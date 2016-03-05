package cn.com.tsjx.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author simier
 * 2013-9-5下午2:43:39
 * 
 */
public class ConstantUtils {
	
	private ConstantUtils(){}
	
	
	
	
	/**
	 * 商品编码
	 */
	public static final String GOODS_CODE="product_code";
	/**
	 * 景区编码
	 */
	public static final String SCENIC_CODE="scenic_code";
	/**
	 * 餐饮编码
	 */
	public static final String REPAST_CODE="repast_code";
	/**
	 * 酒店编码
	 */
	public static final String HOTEL_CODE="hotel_code";
	/**
	 * 订单编码
	 */
	public static final String ORDER_CODE="order_code";
	/**
	 * 甩单编码
	 */
	public static final String CLUSTER_ORDER_CODE="cluster_order_code";
	/**
	 * 退单编码
	 */
	public static final String ORDER_RETREAT="order_retreat";
	/**
	 * 检票编码
	 */
	public static final String CHECK_CODE="check_code";
	/**
	 * 辅助检票编码
	 */
	public static final String ASSIST_CHECK_CODE="assist_check_code";
	/**
	 * 新的辅助码
	 */
	public static final String ASSIST_CHECK_NOS = "ASSIST_CHECK_NOS";
	
	/**
	 * 剧院
	 */
	public static final String THEATER="theater";
	/**
	 * 剧院
	 */
	public static final String SHOW_THEATER_INFO="show_theater_info";
	
	/**
	 * 剧院区域
	 */
	public static final String SHOW_THEATER_AREA="show_theater_area";
	
	/**
	 * 演出
	 */
	public static final String SHOW_INFO="show_info";
	
	/**
	 * 演出票型
	 */
	public static final String SHOW_TICKET_MODEL="show_ticket_model";
	
	/**
	 * 数据库操作map参数key值
	 */
	public static final String CONDITION_PREFIX = "entity";
	
	/**
	 * 前台管理系统标识
	 */
	public static final String FRONT = "FRONT-END";
	
	/**
	 * 后台管理系统标识
	 */
	public static final String END = "AFTER-END";
	
	/**
	 * 服务提供企业编码
	 */
	public static final String SENDINFO = "SENDINFO";
	
	/**
	 * 退票编号
	 */
	public static final String ORDER_RETREAT_CODE="order_retreat_code";
	
	/**
	 * 数据操作动作
	 * @author simier
	 * 2013-9-12下午5:39:08
	 *
	 */
	public interface Operator {
		
		public String ADD = "save";
		
		public String DELETE = "delete";
		
		public String UPDATE = "update";
		
		public String ADDORUPDATE = "saveOrUpdate";
		
		public String SELECT  = "select";
		
	}
	
	/**
	 * @author GY
	 * @Description:查询参数，根据不同的参数进行特殊的查询
	 * @date 2013-10-24 下午2:04:40
	 */
	public interface Params{
		
		public String USED_SCENIC = "查询常用景区";
		
		public String ACCURATE_FIND_BY_NAME = "根据名称精确查询";
		
		public String ENABLE = "产品上架或者下架";
	}
	
	/**
	 * @author GY
	 * @Description:  编码因子编码
	 * @date 2013-10-26 上午10:19:20
	 */
	public interface Code{
		public String SCENIC = "245";
		
		public String REPAST = "249";
		
		public String HOTEL = "216";
		
		public String FAMILY = "246";
		
		public Long THEATER	 = 263L;
		
		public String PRODUCT = "PRODUCT";
		
		public String SCENIC_P = "SCENIC_P";
		
		public String REPAST_P = "REPAST_P";
		
		public String HOTEL_P = "HOTEL_P";
		
		public String COMBINATION_P = "COMBINATION_P";
		
		public String SALECODE = "SALECODE";
		
		public String ORDER = "ORDER";
		
		public String CHECK = "CHECK";
		
		public String ASSIST_CHECK = "ASSIST_CHECK";
		
		public String ORDER_RETREAT = "RETREAT";
		
		public String TRAVELGROUP = "TG";
		
		public String JOURNEYGROUP = "JG";
		
		public String TRAVELORDER = "TO";
		
		public String GROUP_ORDER_DETAIL = "GDD";
		
		public String CHARGE_BACK = "TD";
		
		public String SHOW_P="SHOW_P";
		
		public String THEATER_P="THEATER_P";
		
		public String SHOW_TICKET_P="SHOW_TICKET_P";
		
		//**********产品新的编码方式********
		
		public String SCENIC_TICKET = "PST";
		
		public String REPAST_TICKET = "PRT";
		
		public String HOTEL_TICKET = "PHT";
		
		public String FAMILY_TICKET = "PFT";
		
		public String THEATER_SESSION_CODE = "PTT";
		
		public String LINE_TICKET = "PLT";
		
		//**********建筑物新的编码方式*******
		
		public String BUILD_SCENIC = "BP";
		
		public String BUILD_AREA = "BA";
		
		public String BUILD_REPAST = "BR";
		
		public String BUILD_HOTEL = "BH";
		
		public String BUILD_SYNC = "BS";
		
	}
	
	/**
	 * @author GY
	 * @Description: 库存版本号
	 * @date 2013-10-26 上午11:34:11
	 */
	public interface Version {
		
		public Long INIT = 0l;
		
		public Long INCREMENT = 1l;
	}
	
	/**
	 * @author GY
	 * @Description: 类目id
	 * @date 2013-11-20 下午6:11:54
	 */
	public interface CategoryId {
		public long HOTELROOM = 215l;
		public long COMBINATION = 246l;
		public long SCENICTICKET = 245l;
		public long DINNERICKET = 249l;
		public long LINE_TICKET = 1l;
		public long ROOM_TYPE_TICKET = 216L;
	}
	
	/**
	 * 购买限定类型
	 *
	 */
	public interface PurchaseType{
		/**
		 * 身份证
		 */
		String ID_CARD="idCard";
		/**
		 * 手机
		 */
		String MOBILE="mobile";
	}
	
	/**
	 * 退款类别
	 * 
	 */
	public interface RetreatCategory {
		/**
		 * 固定值
		 */
		String FIXED = "fixed";
		/**
		 * 百分比
		 */
		String PERCENT = "percent";
	}
	
	/**
	 * =========================================================================
	 * =========================================================================
	 * =======================         订单常量	      ========================== 
	 * =========================================================================
	 * =========================================================================
	 */
	
	/**
	 *  默认订单失效时间
	 */
	public static final Long DEFAULT_INVALID_TIME = 86400l;
	
	/**
	 * @author GY
	 * @Description: 订单状态
	 * @date 2013-11-18 下午3:56:35
	 */
	public interface OrderStatus {
		 /**
	     * 有效
	     */
		String VALID="valid";
		/**
		 * 无效
		 */
		String INVALID="invalid";

	}
	
	/**
	 * @author GY
	 * @Description: 订单支付状态
	 * @date 2013-11-18 下午3:56:48
	 */
	public static class PayStatus {
		/**
		 * 等待付款
		 */
		public static final String WAIT_PAY="wait_pay";
		/**
		 * 已支付
		 */
		public static final String PAYED="payed";
		/**
		 * 支付中
		 */
		public static final String PAYING="paying";
		/**
		 * 交易关闭
		 */
		public static final String CLOSE_PAY="close_pay";
		/**
		 * 待退款
		 */
		public static final String WAIT_REFUND="wait_refund"; 
		/**
		 * 已退款
		 */
		public static final String REFUNDED="refunded"; 
		/**
		 * 等待结算
		 */
		public static final String WAIT_SETTLEMENT="wait_settlement";
		/**
		 * 已结算
		 */
		public static final String SETTLEMENTED="settlemented";
		
		public static final Map<String, String> getPayStatus = new HashMap<String, String>();
		
		static{
			PayStatus.getPayStatus.put(PayMethod.ALIPAY, PayStatus.WAIT_PAY);
			PayStatus.getPayStatus.put(PayMethod.VM, PayStatus.PAYED);
			PayStatus.getPayStatus.put(PayMethod.GYT, PayStatus.WAIT_PAY);
			PayStatus.getPayStatus.put(PayMethod.SPOT, PayStatus.WAIT_PAY);
		}
	}
	
	/**
	 * 支付方法
	 * @author crazy_cabbage
	 *
	 */
	public interface PayMethod{
		
		/**
		 * 支付宝
		 */
		String ALIPAY="alipay";
		/**
		 * 备用金
		 */
		String VM="vm";
		/**
		 * 赣游通
		 */
		String GYT="gyt";
		/**
		 * 现场支付
		 */
		String SPOT="spot";
		/**
		 * 签单
		 */
		String SIGN_BILL="sign_bill";
		/**
		 * 在线支付
		 */
		String ONLINE="on_line";
		
	}
	

	public interface OrderType {
		/**
		 * 所有
		 */
		String ALL = "all";
		/**
		 * 充值订单
		 */
		String RECHARGE = "recharge";
		/**
		 * 酒店订单
		 */
		String HOTEL = "hotel";
		/**
		 * 餐饮订单
		 */
		String KITCHEN = "kitchen";
		/**
		 * 门票订单
		 */
		String SCENIC = "scenic";
		/**
		 * 线路订单
		 */
		String LINES = "lines";
		/**
		 * 套票
		 */
		String FAMILY="family";
		
		/**
         * 团购
         */
        String GROUP="group";
	}
	/**
	 * 检票状态
	 * @author crazy_cabbage
	 *
	 */
	public interface CheckStatus{
		/**
		 * 未检票
		 */
		String UN_CHECK="un_check";
		/**
		 * 检票中
		 */
		String CHECKING="checking";
		/**
		 * 检票完成
		 */
		String CHECKED="checked";
		/**
		 * 不需要检票
		 */
		String UN_NEED_CHECK="un_need_check";
		/**
		 * 检票同步中
		 */
		String CHECK_SYNCING="check_syncing";
	}
	
	/**
	 * @author GY
	 * @Description: 检票方式
	 * @date 2013-11-28 上午9:38:48
	 */
	public interface CheckMode {
		/**
		 * 线下
		 */
		String BTL = "btl";
		/**
		 * 线上
		 */
		String ONLINE = "online";
		/**
		 * 终端
		 */
		String TERMINAL = "terminal";
		/**
		 * 闸机
		 */
		String GATE = "gate";
		/**
		 * 淘宝WEB核销
		 */
		String TAOBAO_WEB = "taobao_web";
		
		/**
		 * 平板
		 */
		String FLAT ="flat";
	}
	
	
		
	/**
	 * @author GY
	 * @Description: 订单来源
	 * @date 2013-11-20 下午2:34:07
	 */
	public interface Src{
	   /**
	    * 旅行社平台
	    */
	   String TRAVEL_PLATFORM="travel_platform";
	   /**
	    * 平板
	    */
	   String PAD="pad";
		/**
		 * 接口
		 */
	   String INTERFACE="interface";
	   /**
	    * 平台
	    */
	   String PLATEFORM="plateform";
	   /**
	    * 淘宝
	    */
	   String TAOBAO="taobao";
	   /**
	    * 手机接口
	    */
	   String MOBILE_INTERFACE="mobile_interface";
	}
	
	/**
	 * @author GY
	 * @Description: 身份证
	 * @date 2013-11-20 下午2:37:38
	 */
	public interface CertificateType{
		String ID = "id";
	}
	
	public interface OrderInvalidTime {
		Long DEFAULT_TIME = 2l*60l*60l;
	}
	
	/**
	 * 退款单状态
	 * 
	 */
	public interface RetreatStatus {
		/**
		 * 待审核
		 */
		String WAIT_AUDIT = "wait_audit";
		/**
		 * 不需要审核
		 */
		String NO_AUDIT = "no_audit";
		/**
		 * 审核成功
		 */
		String AUDIT_SUCCESS = "audit_success";
		/**
		 * 审核失败
		 */
		String AUDIT_FAILURE = "audit_failure";

	}
	
	public interface MsgCategory{
		/**
		 * 发码
		 */
		public String SEND_CODE="send_code";
		/**
		 * 人为
		 */
		public String  ARTIFICIAL ="artificial";
		
	}
	
	public interface MsgType{
		/**
		 * 短信
		 */
		public String SMTYPE = "0";
		/**
		 * 彩信
		 */
		public String MMSTYPE = "1";
		
	}
	
	
	
	 public interface SendNoType{
		   /**
		    * QR码
		    */
		   public static String QR="qr";
		   /**
		    * GM码
		    */
		   public static String GM="gm";
		   /**
		    * DM码
		    */
		   public static String DM="dm";
		   
	 }
	
}
