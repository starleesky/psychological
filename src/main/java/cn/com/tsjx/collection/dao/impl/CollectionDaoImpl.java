package cn.com.tsjx.collection.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.collection.dao.CollectionDao;
import cn.com.tsjx.collection.entity.Collection;

@Repository("collectionDao")
public class CollectionDaoImpl extends BaseDaoImpl<Collection, Long> implements CollectionDao {

}
