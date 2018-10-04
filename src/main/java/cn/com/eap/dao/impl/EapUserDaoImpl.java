package cn.com.eap.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.eap.dao.EapUserDao;
import cn.com.eap.entity.EapUser;

@Repository("eapUserDao")
public class EapUserDaoImpl extends BaseDaoImpl<EapUser, Long> implements EapUserDao {

}
