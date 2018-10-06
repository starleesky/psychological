package cn.com.tsjx.common.web.enums;

/**
 * 
 * 
 * 
 * xyl (2015-9-29 上午4:16:50)
 */
public interface IRefundEnum {

	/**
	 * 
	 * 
	 * 
	 * xyl (2015-9-29 上午4:20:46)
	 */
	enum RefundStatus {

		UNDO(-1L, "未退款"), 
		DOING(0L, "退款中"), 
		SUCCESS(1L, "退款[申请]完成"), 
		FAIL(2L, "退款[申请]失败")
		;
		
		public Long code;
		public String name;

		RefundStatus(Long code, String name) {
			this.code = code;
			this.name = name;
		}
	}

}
