package cn.com.tsjx.common.bean.entity.alipay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量支付的数据
 * @author biejunbo
 * @date 2014-6-4 
 *
 */
public class BatchData {

	private static final String ITEM_SEPARATER = "|";
	private static final String FIELD_SEPARATER = "^";

	private List<Item> itemList = new ArrayList<Item>();

	/**
	 * 增加数据项。
	 * @param tradeNo 流水号
	 * @param accntNo 收款账号
	 * @param accntName 收款账户名
	 * @param amount 付款金额
	 * @param remark 备注信息
	 */
	public void addItem(String tradeNo, String accntNo, String accntName, String amount, String remark) {
		this.itemList.add(new Item(tradeNo, accntNo, accntName, amount, remark));
	}

	/**
	 * 返回批次付款总笔数。<br>
	 * 说明：最大支持1000笔。
	 * @return
	 */
	public int getBatchNum() {
		return this.itemList.size();
	}

	/**
	 * 返回批次付款总金额
	 * @return
	 */
	public double getBatchFee() {
		BigDecimal total = BigDecimal.ZERO;
		for (Item item : itemList) {
			Double amount = Double.parseDouble(item.amount);
			total = total.add(BigDecimal.valueOf(amount));
		}
		return total.doubleValue();
	}

	/**
	 * 返回格式后的数据。<br>
	 * 格式：流水号1^帐号1^姓名1^金额1^备注1|流水号2^帐号2^姓名2^金额2^备注2....
	 * @return
	 */
	public String toDataString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < itemList.size(); i++) {
			Item item = itemList.get(i);
			if (i > 0) {
				builder.append(ITEM_SEPARATER);
			}
			builder.append(getSafeString(item.tradeNo)).append(FIELD_SEPARATER);
			builder.append(getSafeString(item.accntNo)).append(FIELD_SEPARATER);
			builder.append(getSafeString(item.accntName)).append(FIELD_SEPARATER);
			builder.append(getSafeString(item.amount)).append(FIELD_SEPARATER);
			builder.append(getSafeString(item.remark));
		}
		return builder.toString();
	}

	private String getSafeString(String value) {
		if (value == null) {
			return "";
		}
		return value.trim();
	}

	public String toString() {
		return this.toDataString();
	}

	/**
	 * 数据项。
	 * @author liwei
	 */
	private static class Item {
		private String tradeNo;
		private String accntNo;
		private String accntName;
		private String amount;
		private String remark;

		public Item(String tradeNo, String accntNo, String accntName, String amount, String remark) {
			this.tradeNo = tradeNo;
			this.accntNo = accntNo;
			this.accntName = accntName;
			this.amount = amount;
			this.remark = remark;
		}
	}

	public static void main(String[] args) {
		BatchData batch = new BatchData();
		batch.addItem("1231", "aaa1", "bbb1", "110", null);
		batch.addItem("1232", "aaa2", "bbb2", "22.1", "");
		batch.addItem("1233", "aaa3", "bbb3", "3.28", "remark");
		batch.addItem("1234", "aaa4", "bbb4", "4.05", null);

		System.out.println(batch.toDataString());
		System.out.println(batch.getBatchNum());
		System.out.println(batch.getBatchFee());
	}
}
