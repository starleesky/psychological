/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: pageOutDto.java
 * Author:   muxing
 * Date:    2016/3/22 0:47
 * Description:
 */
package cn.com.tsjx.demo;

/**
 * pageOutDto
 *
 * @author muxing
 * @date 2016/3/22
 */
public class pageOutDto {

	private String data="                    <li class=\"pro-box\">\n"
			+ "                        <a href=\"view.php\" class=\"pro-img\">\n"
			+ "                            <img src=\"../images/img2.jpg\" />\n"
			+ "                        </a>\n"
			+ "                        <div class=\"pro-info\">\n"
			+ "                            <a href=\"view.php\" class=\"pro-title\">品牌加型号</a>\n"
			+ "                            <strong class=\"pro-price\">5,000.00 元</strong>\n"
			+ "                            <p class=\"pro-date\">\n"
			+ "                                <span class=\"year f-l\">2012年</span>\n"
			+ "                                <span class=\"hourth f-r\">2000小时</span>\n"
			+ "                            </p>\n"
			+ "                            <p>\n"
			+ "                                <span class=\"ready-num f-l\">浏览<em class=\"jRNum\">500次</em></span>\n"
			+ "                                <span class=\"pro-addr f-r\">湖南</span>\n"
			+ "                            </p>\n"
			+ "                        </div>\n"
			+ "                    </li>";

	private int error = 0;

	private String msg;

	private String ver = "0.1";

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}
}
