package cn.com.eap.service.impl;

import cn.com.eap.dao.EapEvaluatingDao;
import cn.com.eap.entity.EapEvaluating;
import cn.com.eap.entity.EapUser;
import cn.com.eap.service.EapEvaluatingService;
import cn.com.eap.service.EapUserService;
import cn.com.eap.web.dto.EapEvaluatingParam;
import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("eapEvaluatingService")
public class EapEvaluatingServiceImpl extends BaseServiceImpl<EapEvaluating, Long> implements EapEvaluatingService {

	@Resource
	private EapEvaluatingDao eapEvaluatingDao;

	@Resource
	EapUserService eapUserService;

	@Override
	protected BaseDao<EapEvaluating, Long> getBaseDao() {
		return this.eapEvaluatingDao;
	}

	@Override
	public boolean submit(EapEvaluatingParam eapEvaluatingParam) {

		EapUser eapUser = new EapUser();
		BeanUtils.copyProperties(eapEvaluatingParam, eapUser);
		eapUser.setEvaluatingCount(1);
		eapUser.setSrc("1"); //评测

		Long userId = eapUserService.relevanceUser(eapUser);
		// TODO: 2018/10/7 答案计算（3中题型）



		return false;
	}
}
