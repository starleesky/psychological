package cn.com.tsjx.attch.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.attch.dao.AttchDao;
import cn.com.tsjx.attch.entity.Attch;
import cn.com.tsjx.attch.service.AttchService;

@Service("attchService")
public class AttchServiceImpl extends BaseServiceImpl<Attch, Long> implements AttchService {

	@Resource
	private AttchDao attchDao;

	@Override
	protected BaseDao<Attch, Long> getBaseDao() {
		return this.attchDao;
	}
}
