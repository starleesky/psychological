package cn.com.eap.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.eap.dao.EapSubscribeDao;
import cn.com.eap.entity.EapSubscribe;
import cn.com.eap.service.EapSubscribeService;

@Service("eapSubscribeService")
public class EapSubscribeServiceImpl extends BaseServiceImpl<EapSubscribe, Long> implements EapSubscribeService {

	@Resource
	private EapSubscribeDao eapSubscribeDao;

	@Override
	protected BaseDao<EapSubscribe, Long> getBaseDao() {
		return this.eapSubscribeDao;
	}
}
