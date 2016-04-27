package cn.com.tsjx.common.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import cn.com.tsjx.common.bean.entity.BaseEntity;
import cn.com.tsjx.common.web.model.Pager;


/**
 * 系统服务基础接口，定义最简单的增删改查功能。
 * @param <T> 数据对象
 * @param <PK> 数据标识
 */
public interface BaseService<T extends BaseEntity<PK>, PK extends java.io.Serializable> {

	/**
	 * 保存数据对象，返回值对象中包含数据库生成的标识
	 * @param entity 数据对象
	 * @return 返回数据对象
	 */
	public T insert(T entity);

	/**
	 * 更新数据对象
	 * @param entity 数据对象
	 * @return 影响行数
	 */
	public int update(T entity);
	
	/**
     * 批量更新数据实体
     * 
     * @param entities
     */
	public int update(Collection<T> entities);

	/**
	 * 根据标识删除数据对象
	 * @param id 标识
	 * @return 影响行数
	 */
	public int delete(PK id);

	/**
	 * 批量删除数据实体
	 * @param entities
	 * @return 影响行数
	 */
	public int delete(Collection<PK> ids);

	/**
	 * 根据标识获取数据对象
	 * @param id 标识
	 * @return
	 */
	public T get(PK id);

	/**
	 * 根据条件查询数据对象。
	 * @return
	 */
	public List<T> find(T entity);

	/**
	 * 返回所有数据对象。
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 根据条件分页查询数据。<br>
	 * 列表数据的类型可查看Mapper文件。
	 * @param page 分页器
	 * @return
	 */
	public <E> Pager<E> page(Pager<E> pager);
	
	   /**
     * 根据条件分页查询数据。<br>
     * 列表数据的类型可查看Mapper文件。
     * @param params 条件
     * @param page 分页器
     * @return
     */
    public <E> Pager<E> page(Map<String,Object> map, Pager<E> pager);
	
	
}