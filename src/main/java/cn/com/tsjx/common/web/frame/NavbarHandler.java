package cn.com.tsjx.common.web.frame;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import cn.com.tsjx.common.bean.dto.Channel;
import cn.com.tsjx.common.context.AppCached;
import cn.com.tsjx.common.context.UserContext;
import cn.com.tsjx.common.web.client.UserHolder;

/**
 * 客户系统导航页面。
 * @author liwei
 */
public class NavbarHandler implements Handler {

	private UserContext userContext;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 模块
		String module = request.getParameter("module");
		request.setAttribute("module", module);
		// 用户来源(自定义Logo)
		String source = UserHolder.getUser().getSource();
		if (StringUtils.isEmpty(source)) {
			source = "boss";
		}
		List<Channel> channelList = this.buildChannels();
		request.setAttribute("source", source);
		request.setAttribute("channelList", channelList);
		return "/navbar.ftl";
	}

	public List<Channel> buildChannels() {
		List<Channel> channelList = new ArrayList<Channel>();
		for (Channel channel : AppCached.getChannelList()) {
			if (channel.getAuthCodes() != null && this.hasChannelAuth(channel)) {
				channelList.add(channel);
			}
		}
		return channelList;
	}

	private boolean hasChannelAuth(Channel channel) {
		for (String authCode : channel.getAuthCodes()) {
			if (this.userContext.hasAuthCode(authCode)) {
				return true;
			}
		}
		return false;
	}

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}
}
