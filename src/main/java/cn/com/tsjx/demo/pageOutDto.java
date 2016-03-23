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

	private String data="                    <li class=\"pro-box\">"
			+ "                        <a href=\"view.php\" class=\"pro-img\">"
			+ "                            <img src=\"../images/img2.jpg\" />"
			+ "                        </a>"
			+ "                        <div class=\"pro-info\">"
			+ "                            <a href=\"view.php\" class=\"pro-title\">品牌加型号</a>"
			+ "                            <strong class=\"pro-price\">5,000.00 元</strong>"
			+ "                            <p class=\"pro-date\">"
			+ "                                <span class=\"year f-l\">2012年</span>"
			+ "                                <span class=\"hourth f-r\">2000小时</span>"
			+ "                            </p>"
			+ "                            <p>"
			+ "                                <span class=\"ready-num f-l\">浏览<em class=\"jRNum\">500次</em></span>"
			+ "                                <span class=\"pro-addr f-r\">湖南</span>"
			+ "                            </p>"
			+ "                        </div>"
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
