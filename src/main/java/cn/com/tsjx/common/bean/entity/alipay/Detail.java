package cn.com.tsjx.common.bean.entity.alipay;

import java.util.Date;

/**
 * 支付宝回调返回来数据
 * @author biejunbo
 * @date 2014-6-4 
 *
 */
public class Detail {

	private String tradeNo; // 智游宝交易号
	private Long amount;
	private String success;
	private String reson;
	private Date notifyTime;
	private String accntNo;
	private String accntName;
	private String outTradeNo; // 支付宝交易号

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getReson() {
		return reson;
	}

	public void setReson(String reson) {
		this.reson = reson;
	}

	public Date getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}

	public String getAccntNo() {
		return accntNo;
	}

	public void setAccntNo(String accntNo) {
		this.accntNo = accntNo;
	}

	public String getAccntName() {
		return accntName;
	}

	public void setAccntName(String accntName) {
		this.accntName = accntName;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
}
