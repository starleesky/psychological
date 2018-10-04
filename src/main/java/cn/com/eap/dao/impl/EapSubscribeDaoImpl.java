package cn.com.eap.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.eap.dao.EapSubscribeDao;
import cn.com.eap.entity.EapSubscribe;

@Repository("eapSubscribeDao")
public class EapSubscribeDaoImpl extends BaseDaoImpl<EapSubscribe, Long> implements EapSubscribeDao {

}
