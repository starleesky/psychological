package cn.com.eap.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.eap.dao.EapWeixinUserDao;
import cn.com.eap.entity.EapWeixinUser;
import cn.com.eap.service.EapWeixinUserService;

@Service("eapWeixinUserService")
public class EapWeixinUserServiceImpl extends BaseServiceImpl<EapWeixinUser, Long> implements EapWeixinUserService {

	@Resource
	private EapWeixinUserDao eapWeixinUserDao;

	@Override
	protected BaseDao<EapWeixinUser, Long> getBaseDao() {
		return this.eapWeixinUserDao;
	}
}
