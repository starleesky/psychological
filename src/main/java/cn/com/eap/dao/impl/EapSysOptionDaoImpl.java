package cn.com.eap.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.eap.dao.EapSysOptionDao;
import cn.com.eap.entity.EapSysOption;

@Repository("eapSysOptionDao")
public class EapSysOptionDaoImpl extends BaseDaoImpl<EapSysOption, Long> implements EapSysOptionDao {

}
