package cn.com.tsjx.common.util.xml;

/**
 * XPath解析XML文件中的解析异常。
 * @author liwei
 */
public class ParserException extends RuntimeException {

	private static final long serialVersionUID = -3765737193925205403L;

	public ParserException() {
		super();
	}

	public ParserException(String message) {
		super(message);
	}

	public ParserException(Throwable cause) {
		super(cause);
	}

	public ParserException(String message, Throwable cause) {
		super(message, cause);
	}
}
