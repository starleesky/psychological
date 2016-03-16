package cn.com.tsjx.goodsCatagory.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.goodsCatagory.dao.GoodsCatagoryDao;
import cn.com.tsjx.goodsCatagory.entity.GoodsCatagory;
import cn.com.tsjx.goodsCatagory.service.GoodsCatagoryService;

@Service("goodsCatagoryService")
public class GoodsCatagoryServiceImpl extends BaseServiceImpl<GoodsCatagory, Long> implements GoodsCatagoryService {

	@Resource
	private GoodsCatagoryDao goodsCatagoryDao;

	@Override
	protected BaseDao<GoodsCatagory, Long> getBaseDao() {
		return this.goodsCatagoryDao;
	}
}
