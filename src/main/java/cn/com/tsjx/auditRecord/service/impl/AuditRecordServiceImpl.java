package cn.com.tsjx.auditRecord.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.auditRecord.dao.AuditRecordDao;
import cn.com.tsjx.auditRecord.entity.AuditRecord;
import cn.com.tsjx.auditRecord.service.AuditRecordService;

@Service("auditRecordService")
public class AuditRecordServiceImpl extends BaseServiceImpl<AuditRecord, Long> implements AuditRecordService {

	@Resource
	private AuditRecordDao auditRecordDao;

	@Override
	protected BaseDao<AuditRecord, Long> getBaseDao() {
		return this.auditRecordDao;
	}
}
