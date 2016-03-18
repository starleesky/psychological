package cn.com.tsjx.auditHis.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.auditHis.dao.AuditHisDao;
import cn.com.tsjx.auditHis.entity.AuditHis;
import cn.com.tsjx.auditHis.service.AuditHisService;

@Service("auditHisService")
public class AuditHisServiceImpl extends BaseServiceImpl<AuditHis, Long> implements AuditHisService {

	@Resource
	private AuditHisDao auditHisDao;

	@Override
	protected BaseDao<AuditHis, Long> getBaseDao() {
		return this.auditHisDao;
	}
}
