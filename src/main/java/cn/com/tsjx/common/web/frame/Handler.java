package cn.com.tsjx.common.web.frame;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Handler {

	/**
	 * 业务处理
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
