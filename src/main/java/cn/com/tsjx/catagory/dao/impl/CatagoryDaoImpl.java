package cn.com.tsjx.catagory.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.catagory.dao.CatagoryDao;
import cn.com.tsjx.catagory.entity.Catagory;

@Repository("catagoryDao")
public class CatagoryDaoImpl extends BaseDaoImpl<Catagory, Long> implements CatagoryDao {

}
