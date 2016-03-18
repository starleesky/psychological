package cn.com.tsjx.auditHis.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.auditHis.dao.AuditHisDao;
import cn.com.tsjx.auditHis.entity.AuditHis;

@Repository("auditHisDao")
public class AuditHisDaoImpl extends BaseDaoImpl<AuditHis, Long> implements AuditHisDao {

}
