package cn.com.eap.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.eap.dao.EapMsgDao;
import cn.com.eap.entity.EapMsg;
import cn.com.eap.service.EapMsgService;

@Service("eapMsgService")
public class EapMsgServiceImpl extends BaseServiceImpl<EapMsg, Long> implements EapMsgService {

	@Resource
	private EapMsgDao eapMsgDao;

	@Override
	protected BaseDao<EapMsg, Long> getBaseDao() {
		return this.eapMsgDao;
	}
}
