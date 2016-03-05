package cn.com.tsjx.common.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;

import cn.com.tsjx.common.bean.entity.BaseEntity;
import cn.com.tsjx.common.bean.entity.VersionEntity;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.common.web.model.Params;



/**
 * 实体数据访问通用方法定义。
 * @author andy
 * @param <T> 数据实体类型
 * @param <PK> 数据主键类型
 */
public abstract class BaseDaoImpl<T extends BaseEntity<PK>, PK extends java.io.Serializable> extends SqlSessionDao implements BaseDao<T, PK> {

	/**
	 * 插入数据实体
	 * @param entity 数据实体
	 * @return 影响行数
	 */
	public int insert(T entity) {
		if (entity instanceof VersionEntity) {
			// 插入实体时将实体的版本号设置为1.
			((VersionEntity) entity).setVersion(1L);
		}
//		entity.setCreateTime(Utility.getNow());
		return this.insert(getMethodName(), entity);
	}

	/**
	 * 批量插入数据。
	 * @param entities
	 */
	public void insert(Collection<T> entities) {
		for (T entity : entities) {
//			entity.setCreateTime(Utility.getNow());
			this.insert(entity);
		}
	}

	/**
	 * 更新数据实体
	 * @param entity 数据实体
	 * @return 影响行数
	 */
	public int update(T entity) {
//		entity.setModifyTime(Utility.getNow());
		int rownum = this.update(getMethodName(), entity);
		if (entity instanceof VersionEntity && rownum == 0) {
			// 更新数据时，如果影响行数为0时，抛出异常
			throw new OptimisticLockingFailureException("Object of class [" + entity.getClass().getName()
					+ "] with identifier [" + entity.getId() + "]: optimistic locking failed");
		}
		return rownum;
	}

	/**
	 * 批量更新数据，暂不支持乐观锁。
	 * @param entities
	 */
	public int update(Collection<T> entities) {
		int i_affect = 0;
		for (T entity : entities) {
//			entity.setModifyTime(Utility.getNow());
			i_affect += this.update(entity);
		}
		return i_affect;
	}

	/**
	 * 删除数据实体，暂不支持乐观锁。
	 * @param id 数据标识
	 * @return 影响行数
	 */
	public int delete(PK id) {
		Params params = Params.create().add("id", id);
		return this.delete(getMethodName(), params);
	}

	/**
	 * 批量更新数据，暂不支持乐观锁。
	 * @param entities
	 */
	public int delete(Collection<PK> ids) {
		int i_affect = 0;
		for (PK id : ids) {
			i_affect += this.delete(id);
		}
		return i_affect;
	}

	/**
	 * 根据标识查询数据实体
	 * @param id 数据标识
	 * @return
	 */
	public T get(PK id) {
		Params params = Params.create().add("id", id);
		return this.<T> selectOne(getMethodName(), params);
	}

	/**
	 * 根据条件查询数据对象。
	 * @return
	 */
	public List<T> find(T entity) {
		return this.<T> selectList(getMethodName(), entity);
	}

	/**
	 * 查询所有数据实体
	 * @return
	 */
	public List<T> findAll() {
		return this.<T> selectList(getMethodName(), Params.create());
	}

	/**
	 * 分页查询所有能查看数据列表
	 * @param params 条件
	 * @param page 分页器
	 * @return
	 */
	public <E> Pager<E> page(Pager<E> pager) {
	    Params params=Params.create();
		return this.selectPage(getMethodName(), params, pager);
	}
}
