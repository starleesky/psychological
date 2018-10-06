package cn.com.tsjx.common.enums;

/**
 * 枚举接口.
 * @author 楠木
 */
public interface IEnum<T> {

	/**
	 * 返回枚举值。
	 * @return
	 */
	public T value();

	/**
	 * 返回枚举名
	 * @return
	 */
	public String label();
}
