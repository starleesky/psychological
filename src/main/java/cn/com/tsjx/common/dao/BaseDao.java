package cn.com.tsjx.common.dao;

import java.util.Collection;
import java.util.List;

import cn.com.tsjx.common.bean.entity.BaseEntity;
import cn.com.tsjx.common.web.model.Pager;



/**
 * 实体数据访问通用方法定义。
 * @author andy
 * @param <T> 数据实体类型
 * @param <PK> 数据主键类型
 */
public interface BaseDao<T extends BaseEntity<PK>, PK extends java.io.Serializable> {

	/**
	 * 插入数据实体
	 * @param entity 数据实体
	 * @return 影响行数（此处应该是返回其ID）
	 */
	public int insert(T entity);

	/**
	 * 批量插入数据。
	 * @param entities
	 */
	public void insert(Collection<T> entities);

	/**
	 * 更新数据实体
	 * @param entity 数据实体
	 * @return 影响行数
	 */
	public int update(T entity);

	/**
	 * 批量更新数据，暂不支持乐观锁。
	 * @param entities
	 * @return 影响行数
	 */
	public int update(Collection<T> entities);

	/**
	 * 删除数据实体，暂不支持乐观锁。
	 * @param id 数据标识
	 * @return 影响行数
	 */
	public int delete(PK id);

	/**
	 * 批量更新数据，暂不支持乐观锁。
	 * @param entities
	 * @return 影响行数
	 */
	public int delete(Collection<PK> ids);

	/**
	 * 根据标识查询数据实体
	 * @param id 数据标识
	 * @return
	 */
	public T get(PK id);

	/**
	 * 根据条件查询数据对象。
	 * @return
	 */
	public List<T> find(T entity);

	/**
	 * 查询所有数据实体
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 分页查询所有能查看数据列表
	 * @param params 条件
	 * @param page 分页器
	 * @return
	 */
	public <E> Pager<E> page(Pager<E> pager);
}
