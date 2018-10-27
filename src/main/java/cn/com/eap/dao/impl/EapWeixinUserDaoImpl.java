package cn.com.eap.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.eap.dao.EapWeixinUserDao;
import cn.com.eap.entity.EapWeixinUser;

@Repository("eapWeixinUserDao")
public class EapWeixinUserDaoImpl extends BaseDaoImpl<EapWeixinUser, Long> implements EapWeixinUserDao {

}
