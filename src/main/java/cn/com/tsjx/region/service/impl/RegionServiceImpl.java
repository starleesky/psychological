package cn.com.tsjx.region.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.region.dao.RegionDao;
import cn.com.tsjx.region.entity.Region;
import cn.com.tsjx.region.service.RegionService;

@Service("regionService")
public class RegionServiceImpl extends BaseServiceImpl<Region, Long> implements RegionService {

	@Resource
	private RegionDao regionDao;

	@Override
	protected BaseDao<Region, Long> getBaseDao() {
		return this.regionDao;
	}
}
