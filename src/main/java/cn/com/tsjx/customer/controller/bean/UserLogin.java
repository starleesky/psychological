/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: UserLogin.java
 * Author:   muxing
 * Date:    2016/3/9 23:06
 * Description:
 */
package cn.com.tsjx.customer.controller.bean;

/**
 * UserLogin
 *
 * @author muxing
 * @date 2016/3/9
 */
public class UserLogin {

	/**
	 * 用户姓名
	 */
	private String username;

	/**
	 * 用户密码
	 */
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
