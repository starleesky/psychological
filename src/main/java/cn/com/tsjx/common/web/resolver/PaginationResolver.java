package cn.com.tsjx.common.web.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import cn.com.tsjx.common.web.model.Page;

/**
 * 分页参数自动填充处理器。
 * @author liwei
 */
public class PaginationResolver implements WebArgumentResolver {

	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest webRequest) throws Exception {
		// 参数对象为com.sendinfo.base.model.Page
		if (methodParameter.getParameterType() == Page.class) {
			String start = webRequest.getParameter("start");
			String count = webRequest.getParameter("count");
			String sort = webRequest.getParameter("sort");
			if (start != null) {
				if (sort == null || sort.isEmpty()) {
					// 不排序
					return new Page(Integer.parseInt(start), Integer.parseInt(count));
				} else {
					// 排序
					String fsort = null;
					String order = Page.ORDER_ASC;
					if (sort.charAt(0) == '-') {
						fsort = sort.substring(1);
						order = Page.ORDER_DESC;
					} else {
						fsort = sort;
						order = Page.ORDER_ASC;
					}
					return new Page(Integer.parseInt(start), Integer.parseInt(count), fsort, order);
				}
			}
			return null;
		}
		return UNRESOLVED;
	}
}
