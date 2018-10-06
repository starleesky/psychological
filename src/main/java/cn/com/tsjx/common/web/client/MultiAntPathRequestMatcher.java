package cn.com.tsjx.common.web.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

/**
 * 多个请求表达式的集合，验证时如果有一个匹配即返回true，否则返回false。
 * @author liwei
 * @see org.springframework.security.web.util.AntPathRequestMatcher
 */
public class MultiAntPathRequestMatcher implements RequestMatcher {

	private List<RequestMatcher> matchers = new ArrayList<RequestMatcher>();

	/**
	 * Creates a matcher with the specific pattern which will match all HTTP methods.
	 * @param pattern the ant pattern to use for matching, split is (,).
	 */
	public MultiAntPathRequestMatcher(String pattern) {
		this(pattern, null);
	}

	/**
	 * Creates a matcher with the supplied pattern which will match all HTTP methods.
	 * @param pattern the ant pattern to use for matching, split is (,).
	 * @param httpMethod the HTTP method. The {@code matches} method will return false if the
	 *        incoming request doesn't have the same method.
	 */
	public MultiAntPathRequestMatcher(String pattern, String httpMethod) {
		String[] patterns = pattern.split(",");
		for (String string : patterns) {
			this.matchers.add(new AntPathRequestMatcher(string, httpMethod));
		}
	}

	public boolean matches(HttpServletRequest request) {
		for (RequestMatcher matcher : this.matchers) {
			if (matcher.matches(request)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MultiAntPathRequestMatcher)) {
			return false;
		}
		MultiAntPathRequestMatcher other = (MultiAntPathRequestMatcher) obj;
		if (this.matchers.size() != other.matchers.size()) {
			return false;
		}
		for (int i = 0; i < this.matchers.size(); i++) {
			Object matcherThis = this.matchers.get(i);
			Object matcherOther = other.matchers.get(i);
			if (!matcherThis.equals(matcherOther)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int code = 31;
		for (RequestMatcher matcher : this.matchers) {
			code ^= matcher.hashCode();
		}
		return code;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Multi {");

		boolean appendFlag = false;
		for (RequestMatcher matcher : this.matchers) {
			if (appendFlag) {
				sb.append(", ");
			} else {
				appendFlag = true;
			}
			sb.append(matcher.toString());
		}

		sb.append("}");

		return sb.toString();
	}
}
