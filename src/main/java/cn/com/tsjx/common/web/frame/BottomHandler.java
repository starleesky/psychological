package cn.com.tsjx.common.web.frame;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import cn.com.tsjx.common.web.client.UserHolder;


/**
 * 客户系统页面底部。
 * @author liwei
 */
public class BottomHandler implements Handler {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 用户来源(自定义Logo)
		String source = UserHolder.getUser().getSource();
		if (StringUtils.isEmpty(source)) {
			source = "boss";
		}
		request.setAttribute("source", source);
		return "/bottom.ftl";
	}

}
