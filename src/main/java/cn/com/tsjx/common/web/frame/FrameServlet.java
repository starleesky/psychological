package cn.com.tsjx.common.web.frame;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class FrameServlet extends HttpServlet {

	private static final long serialVersionUID = 1959623476028762873L;
	
	private static final Logger logger = LoggerFactory.getLogger(FrameServlet.class);

	private FreemarkerEngine freemarkerEngine = new FreemarkerEngine();

	protected Handler getHandler(String name) {
		ServletContext sc = getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		return wac.getBean(name, Handler.class);
	}

	private void doHandler(Handler handler, HttpServletRequest request, HttpServletResponse response) {
		try {
			String viewName = handler.execute(request, response);
			this.freemarkerEngine.doRender(viewName, new HashMap<String, Object>(), request, response,
					getServletContext());
		} catch (Exception e) {
			logger.error("Frame Handler Error.", e);
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String name = request.getParameter("act");
		if (name != null) {
			Handler handler = this.getHandler(name);
			if (handler != null) {
				this.doHandler(handler, request, response);
				return;
			}
		}
		response.getWriter().write("unsupport action.");
	}
}
