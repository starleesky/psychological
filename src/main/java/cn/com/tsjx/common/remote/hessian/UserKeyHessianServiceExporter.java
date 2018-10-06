//package cn.com.tsjx.common.remote.hessian;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.remoting.caucho.HessianServiceExporter;
//
//import cn.com.tsjx.common.web.client.UserHolder;
//
//
//public class UserKeyHessianServiceExporter extends HessianServiceExporter {
//
//	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
//			IOException {
//		this.preInvoke(request, response);
//		try {
//			super.handleRequest(request, response);
//		} finally {
//			UserHolder.clear();
//		}
//		this.postInvoke(request, response);
//	}
//
//	protected void preInvoke(HttpServletRequest request, HttpServletResponse response) {
//		String userKey = request.getHeader(UserHolder.CONTEXT_USER_KEY);
//		if (StringUtils.isNotBlank(userKey)) {
//			UserHolder.setUserKey(userKey);
//		}
//	}
//
//	protected void postInvoke(HttpServletRequest request, HttpServletResponse response) {
//	}
//}