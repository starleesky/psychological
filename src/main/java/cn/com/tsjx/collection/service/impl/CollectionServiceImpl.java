package cn.com.tsjx.collection.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.collection.dao.CollectionDao;
import cn.com.tsjx.collection.entity.Collection;
import cn.com.tsjx.collection.service.CollectionService;

@Service("collectionService")
public class CollectionServiceImpl extends BaseServiceImpl<Collection, Long> implements CollectionService {

	@Resource
	private CollectionDao collectionDao;

	@Override
	protected BaseDao<Collection, Long> getBaseDao() {
		return this.collectionDao;
	}
}
