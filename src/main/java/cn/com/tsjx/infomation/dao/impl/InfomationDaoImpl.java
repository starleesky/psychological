package cn.com.tsjx.infomation.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.infomation.dao.InfomationDao;
import cn.com.tsjx.infomation.entity.Infomation;

@Repository("infomationDao")
public class InfomationDaoImpl extends BaseDaoImpl<Infomation, Long> implements InfomationDao {

}
