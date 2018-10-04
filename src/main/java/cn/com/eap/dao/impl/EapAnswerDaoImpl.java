package cn.com.eap.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.eap.dao.EapAnswerDao;
import cn.com.eap.entity.EapAnswer;

@Repository("eapAnswerDao")
public class EapAnswerDaoImpl extends BaseDaoImpl<EapAnswer, Long> implements EapAnswerDao {

}
