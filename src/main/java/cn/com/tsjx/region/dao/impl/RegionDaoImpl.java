package cn.com.tsjx.region.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.region.dao.RegionDao;
import cn.com.tsjx.region.entity.Region;

@Repository("regionDao")
public class RegionDaoImpl extends BaseDaoImpl<Region, Long> implements RegionDao {

}
