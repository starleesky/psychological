package cn.com.tsjx.auditRecord.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.auditRecord.dao.AuditRecordDao;
import cn.com.tsjx.auditRecord.entity.AuditRecord;

@Repository("auditRecordDao")
public class AuditRecordDaoImpl extends BaseDaoImpl<AuditRecord, Long> implements AuditRecordDao {

}
