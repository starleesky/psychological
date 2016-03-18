package cn.com.tsjx.models.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.models.dao.ModelsDao;
import cn.com.tsjx.models.entity.Models;
import cn.com.tsjx.models.service.ModelsService;

@Service("modelsService")
public class ModelsServiceImpl extends BaseServiceImpl<Models, Long> implements ModelsService {

	@Resource
	private ModelsDao modelsDao;

	@Override
	protected BaseDao<Models, Long> getBaseDao() {
		return this.modelsDao;
	}
}
