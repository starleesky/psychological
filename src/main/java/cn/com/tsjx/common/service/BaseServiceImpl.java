package cn.com.tsjx.common.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tsjx.common.bean.entity.BaseEntity;
import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.web.model.Pager;

/**
 * 系统服务基础接口，定义最简单的增删改查功能。
 * 
 * @param <T>
 *            数据实体
 * @param <PK>
 *            数据标识
 */
public abstract class BaseServiceImpl<T extends BaseEntity<PK>, PK extends java.io.Serializable> implements
        BaseService<T, PK> {

    /**
     * 返回Mapper实体。
     * 
     * @return
     */
    protected abstract BaseDao<T, PK> getBaseDao();

    /**
     * 保存数据实体
     * 
     * @param entity
     *            数据实体
     */
    @Transactional
    public T insert(T entity) {
        this.getBaseDao().insert(entity);
        return entity;
    }

    /**
     * 批量插入数据实体
     * 
     * @param entities
     */
    @Transactional
    public void insert(Collection<T> entities) {
        this.getBaseDao().insert(entities);
    }

    /**
     * 更新数据实体
     * 
     * @param entity
     *            数据实体
     */
    @Transactional
    public int update(T entity) {
    	return this.getBaseDao().update(entity);
    }

    /**
     * 批量更新数据实体
     * 
     * @param entities
     */
    @Transactional
    public int update(Collection<T> entities) {
        return this.getBaseDao().update(entities);
    }

    /**
     * 根据标识删除数据实体
     * 
     * @param id
     *            标识
     */
    @Transactional
    public int delete(PK id) {
        return this.getBaseDao().delete(id);
    }

    /**
     * 批量删除数据实体
     * 
     * @param entities
     */
    @Transactional
    public int delete(Collection<PK> ids) {
        return this.getBaseDao().delete(ids);
    }

    /**
     * 根据标识获取数据实体
     * 
     * @param id
     *            标识
     * @return
     */
    public T get(PK id) {
        return this.getBaseDao().get(id);
    }

    /**
     * 根据条件查询数据对象。
     * 
     * @return
     */
    public List<T> find(T entity) {
        return this.getBaseDao().find(entity);
    }

    /**
     * 返回所有数据对象。
     * 
     * @return
     */
    public List<T> findAll() {
        return this.getBaseDao().findAll();
    }

    /**
     * 根据条件分页查询数据
     * 
     * @param params
     *            条件
     * @param page
     *            分页器
     * @return
     */
    public <E> Pager<E> page(Pager<E> pager) {
        return this.getBaseDao().page(pager);
    }

    @Override
    public <E> Pager<E> page(Map<String, Object> map, Pager<E> pager) {
        pager.setEntity((E)map.get("entity"));
        return this.getBaseDao().page(pager);
    }
    
    
}