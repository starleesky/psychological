package cn.com.tsjx.infomation.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.infomation.dao.InfomationDao;
import cn.com.tsjx.infomation.entity.Infomation;
import cn.com.tsjx.infomation.service.InfomationService;

@Service("infomationService")
public class InfomationServiceImpl extends BaseServiceImpl<Infomation, Long> implements InfomationService {

	@Resource
	private InfomationDao infomationDao;

	@Override
	protected BaseDao<Infomation, Long> getBaseDao() {
		return this.infomationDao;
	}
}
