package cn.com.eap.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.eap.dao.EapAnswerDao;
import cn.com.eap.entity.EapAnswer;
import cn.com.eap.service.EapAnswerService;

@Service("eapAnswerService")
public class EapAnswerServiceImpl extends BaseServiceImpl<EapAnswer, Long> implements EapAnswerService {

	@Resource
	private EapAnswerDao eapAnswerDao;

	@Override
	protected BaseDao<EapAnswer, Long> getBaseDao() {
		return this.eapAnswerDao;
	}
}
