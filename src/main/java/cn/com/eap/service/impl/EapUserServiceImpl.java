package cn.com.eap.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.eap.dao.EapUserDao;
import cn.com.eap.entity.EapUser;
import cn.com.eap.service.EapUserService;

@Service("eapUserService")
public class EapUserServiceImpl extends BaseServiceImpl<EapUser, Long> implements EapUserService {

	@Resource
	private EapUserDao eapUserDao;

	@Override
	protected BaseDao<EapUser, Long> getBaseDao() {
		return this.eapUserDao;
	}
}
