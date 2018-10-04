package cn.com.eap.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.eap.dao.EapEvaluatingDao;
import cn.com.eap.entity.EapEvaluating;

@Repository("eapEvaluatingDao")
public class EapEvaluatingDaoImpl extends BaseDaoImpl<EapEvaluating, Long> implements EapEvaluatingDao {

}
