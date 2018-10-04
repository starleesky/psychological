package cn.com.eap.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.eap.dao.EapEvaluatingDao;
import cn.com.eap.entity.EapEvaluating;
import cn.com.eap.service.EapEvaluatingService;

@Service("eapEvaluatingService")
public class EapEvaluatingServiceImpl extends BaseServiceImpl<EapEvaluating, Long> implements EapEvaluatingService {

	@Resource
	private EapEvaluatingDao eapEvaluatingDao;

	@Override
	protected BaseDao<EapEvaluating, Long> getBaseDao() {
		return this.eapEvaluatingDao;
	}
}
