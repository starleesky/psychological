package cn.com.tsjx.attch.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.attch.dao.AttchDao;
import cn.com.tsjx.attch.entity.Attch;

@Repository("attchDao")
public class AttchDaoImpl extends BaseDaoImpl<Attch, Long> implements AttchDao {

}
