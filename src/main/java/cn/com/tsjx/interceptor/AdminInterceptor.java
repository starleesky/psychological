package cn.com.tsjx.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.record.common.UnicodeString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.tsjx.common.util.StringUtil;
import cn.com.tsjx.user.entity.User;

public class AdminInterceptor extends HandlerInterceptorAdapter {
	
	
	
	private Logger log = LoggerFactory.getLogger(AdminInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		log.info("request:"+request.getRequestURI());
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("adminUser");
		if(user == null || user.getId() == null){
		    response.sendRedirect(request.getContextPath()+"/admin/login");
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info(request.getRequestURI());
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
}