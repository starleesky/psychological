package cn.com.tsjx.common.bean.entity;

public interface GroupEntity {

	/**
	 * 设置企业编码
	 * @param corpCode 企业编码
	 */
	public void setCorpCode(String corpCode);

	/**
	 * 获得企业编码
	 * @return 企业编码
	 */
	public String getCorpCode();

	/**
	 * 设置企业集团编码
	 * @param corpGroupCode 企业集团编码
	 */
	public void setCorpGroupCode(String corpGroupCode);

	/**
	 * 获得企业集团编码
	 * @return 企业集团编码
	 */
	public String getCorpGroupCode();
}
