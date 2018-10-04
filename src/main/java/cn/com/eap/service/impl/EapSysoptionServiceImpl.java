package cn.com.eap.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.eap.dao.EapSysoptionDao;
import cn.com.eap.entity.EapSysoption;
import cn.com.eap.service.EapSysoptionService;

@Service("eapSysoptionService")
public class EapSysoptionServiceImpl extends BaseServiceImpl<EapSysoption, > implements EapSysoptionService {

	@Resource
	private EapSysoptionDao eapSysoptionDao;

	@Override
	protected BaseDao<EapSysoption, > getBaseDao() {
		return this.eapSysoptionDao;
	}
}
