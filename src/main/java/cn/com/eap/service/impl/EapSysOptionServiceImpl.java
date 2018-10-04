package cn.com.eap.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.eap.dao.EapSysOptionDao;
import cn.com.eap.entity.EapSysOption;
import cn.com.eap.service.EapSysOptionService;

@Service("eapSysOptionService")
public class EapSysOptionServiceImpl extends BaseServiceImpl<EapSysOption, Long> implements EapSysOptionService {

	@Resource
	private EapSysOptionDao eapSysOptionDao;

	@Override
	protected BaseDao<EapSysOption, Long> getBaseDao() {
		return this.eapSysOptionDao;
	}
}
