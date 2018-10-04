package cn.com.eap.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.eap.dao.EapMsgDao;
import cn.com.eap.entity.EapMsg;

@Repository("eapMsgDao")
public class EapMsgDaoImpl extends BaseDaoImpl<EapMsg, Long> implements EapMsgDao {

}
