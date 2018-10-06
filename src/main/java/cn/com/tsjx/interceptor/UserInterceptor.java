package cn.com.tsjx.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.tsjx.common.util.StringUtil;
import cn.com.tsjx.notice.entity.Notice;
import cn.com.tsjx.notice.service.NoticeService;
import cn.com.tsjx.user.entity.User;

public class UserInterceptor extends HandlerInterceptorAdapter {
	
	
	
	private Logger log = LoggerFactory.getLogger(UserInterceptor.class);

	@Resource NoticeService noticeService;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	    log.info("拦截开始--"+ request.getRequestURI());
		HttpSession session = request.getSession();
		if (session != null) {
		    User user = null;
		    if (StringUtil.isNotTrimBlank(request.getRequestURI()) && request.getRequestURI().contains("admin")) {
                user = (User) session.getAttribute("adminUser");
       
                if (user == null || user.getId() == null ) {
                    log.info("拦截成功--"+request.getContextPath()+request.getRequestURI());
                    //response.sendRedirect(request.getContextPath()+"/manage/logout.htm");
                    request.getRequestDispatcher("/manage/logout.htm").forward(request, response);
                    return false;

                }
            }else {
                user = (User) session.getAttribute("user");
                if(user == null || user.getId() == null){
                    log.info("拦截成功--"+request.getRequestURI());
                    //response.sendRedirect(request.getContextPath()+"/wap/login.htm");
                    request.getRequestDispatcher("/wap/login.htm").forward(request, response);
                    return false;
                }
            }
		    Notice  notice = new Notice();
		    notice.setUserId(user.getId());
		    List<Notice> list = noticeService.find(notice);
		    if (!list.isEmpty()) {
                session.setAttribute("isNewMessage", true);
            }
        }
		
	    log.info("拦截结束--"+ request.getRequestURI());
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