package cn.com.tsjx.common.web.frame;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import cn.com.tsjx.common.util.web.WebUtils;
import cn.com.tsjx.common.web.client.UserHolder;

/**
 * 客户系统顶部页面。
 * @author liwei
 */
public class TopbarHandler implements Handler {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 用户来源(自定义Logo)
		String source = UserHolder.getUser().getSource();
		if (StringUtils.isEmpty(source)) {
			source = "boss";
		}
		request.setAttribute("source", source);
		request.setAttribute("returnGroup", UserHolder.getUser().getReturnGroup());
		request.setAttribute("domain", WebUtils.getCookieDomain(request));
		return "/topbar.ftl";
	}

}
