package cn.com.tsjx.sysOption.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.sysOption.dao.SysoptionDao;
import cn.com.tsjx.sysOption.entity.Sysoption;
import cn.com.tsjx.sysOption.service.SysoptionService;

@Service("sysoptionService")
public class SysoptionServiceImpl extends BaseServiceImpl<Sysoption, Long> implements SysoptionService {

	@Resource
	private SysoptionDao sysoptionDao;

	@Override
	protected BaseDao<Sysoption, Long> getBaseDao() {
		return this.sysoptionDao;
	}
}
