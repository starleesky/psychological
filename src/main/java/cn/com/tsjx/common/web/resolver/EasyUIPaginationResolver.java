package cn.com.tsjx.common.web.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import cn.com.tsjx.common.web.model.Page;

/**
 * 分页参数自动填充处理器。
 * @author liwei
 */
public class EasyUIPaginationResolver implements WebArgumentResolver {

	public Object resolveArgument(MethodParameter methodParameter, NativeWebRequest request) throws Exception {
		// 参数对象为com.sendinfo.base.model.Page
		if (methodParameter.getParameterType() == Page.class) {
			int page = this.getIntParameter(request, "page", 10);
			int rows = this.getIntParameter(request, "rows", 1);

			Page _page = new Page((page - 1) * rows, rows);
			String sort = request.getParameter("sort");
			if (sort != null && !sort.isEmpty()) {
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
				_page.setSort(fsort);
				_page.setOrder(order);
			}
			return _page;
		}
		return UNRESOLVED;
	}

	public int getIntParameter(NativeWebRequest request, String name, int defaultVal) {
		String value = request.getParameter(name);
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultVal;
		}
	}
}
