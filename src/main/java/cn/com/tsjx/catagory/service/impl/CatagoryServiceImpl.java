package cn.com.tsjx.catagory.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.catagory.dao.CatagoryDao;
import cn.com.tsjx.catagory.entity.Catagory;
import cn.com.tsjx.catagory.service.CatagoryService;

@Service("catagoryService")
public class CatagoryServiceImpl extends BaseServiceImpl<Catagory, Long> implements CatagoryService {

	@Resource
	private CatagoryDao catagoryDao;

	@Override
	protected BaseDao<Catagory, Long> getBaseDao() {
		return this.catagoryDao;
	}
}
