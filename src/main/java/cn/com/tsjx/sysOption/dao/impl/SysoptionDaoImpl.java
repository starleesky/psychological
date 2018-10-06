package cn.com.tsjx.sysOption.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.sysOption.dao.SysoptionDao;
import cn.com.tsjx.sysOption.entity.Sysoption;

@Repository("sysoptionDao")
public class SysoptionDaoImpl extends BaseDaoImpl<Sysoption, Long> implements SysoptionDao {

}
