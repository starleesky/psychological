package cn.com.tsjx.common.bean.dto;

import java.util.List;

/**
 * 渠道
 * @author biejunbo
 * @date 2014-6-4 
 *
 */
public class Channel extends BaseDto {

	private static final long serialVersionUID = -968404398728681662L;

	private String name;
	private String code;
	private String domain;
	private String href;
	private List<String> authCodes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<String> getAuthCodes() {
		return authCodes;
	}

	public void setAuthCodes(List<String> authCodes) {
		this.authCodes = authCodes;
	}
}
