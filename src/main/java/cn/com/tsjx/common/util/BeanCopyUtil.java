package cn.com.tsjx.common.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.tsjx.common.web.model.DataGrid;

/**
 * 对象属性拷贝
 * @Type BeanCopyUtil
 * @Desc 
 * @author hefan
 * @date 2015年5月1日
 * @Version V1.0
 */
public class BeanCopyUtil {

	private static final Logger logger = LoggerFactory.getLogger(BeanCopyUtil.class);

	/**
	 * 对象拷贝操作
	 * @param s要拷贝的源对象
	 * @param t要拷贝的目标对象的类型
	 * @return 返回目标对象
	 */
	public static <S, T> T copy(S s, Class<T> t) {

		if (s == null || t == null) {
			return null;
		}

		try {

			T nt = t.newInstance();

			PropertyUtils.copyProperties(nt, s);

			return nt;

		} catch (InstantiationException e) {

			logger.error("========= 拷贝时发生实例化对象异常 {}", e.toString());
			return null;

		} catch (IllegalAccessException e) {

			logger.error("========= 拷贝时发生非法访问异常异常 {}", e.toString());
			return null;

		} catch (InvocationTargetException e) {

			logger.error("========= 拷贝时发生调用目标对象异常 {}", e.toString());
			return null;

		} catch (Exception e) {

			logger.error("======== 对象 {} 和 {} 拷贝是发生错误 {}",
					new Object[] { s.getClass(), t, e.toString() });
			return null;

		}

	}

	/**
	 * 集合拷贝操作
	 * 
	 * @param s
	 *            要拷贝的源集合
	 * @param t
	 *            要拷贝的目标对象类型
	 * @return 返回目标集合
	 */
	public static <S, T> List<T> copy(List<S> s, Class<T> t) {

		List<T> lnt = new ArrayList<T>();

		for (S ss : s) {

			lnt.add(copy(ss, t));

		}

		return lnt;

	}

	/**
	 * 集合拷贝操作
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static <S, T> List<T> copy(Collection<S> s, Class<T> t) {

		List<T> lnt = new ArrayList<T>();

		for (S ss : s) {

			lnt.add(copy(ss, t));

		}

		return lnt;

	}

	/**
	 * Map拷贝
	 * 
	 * @param s
	 *            Map集合
	 * @param t
	 *            Map集合中的value集合中的对象类型
	 * @return 返回目标Map集合
	 */
	public static <S, T, N> Map<S, List<N>> copy(Map<S, List<T>> s, Class<N> t) {

		Map<S, List<N>> map = new HashMap<S, List<N>>();

		Set<Map.Entry<S, List<T>>> set = s.entrySet();
		for (Map.Entry<S, List<T>> e : set) {
			map.put(e.getKey(), copy(e.getValue(), t));
		}

		return map;

	}

	/**
	 * 分页对象拷贝
	 * 
	 * @param dataGrid
	 * @param t
	 * @return
	 */
	public static <E, T> DataGrid<T> copy(DataGrid<E> dataGrid, Class<T> t) {
		DataGrid<T> dg = new DataGrid<T>();
		dg.setIdentified(dataGrid.getIdentified());
		dg.setNumRows(dataGrid.getNumRows());
		dg.setItems(copy(dataGrid.getItems(), t));
		return dg;
	}

}
