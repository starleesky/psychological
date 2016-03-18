package cn.com.tsjx.goodsCatagory.dao.impl;

import org.springframework.stereotype.Repository;

import com.hp.hpl.sparta.xpath.ThisNodeTest;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.goodsCatagory.dao.GoodsCatagoryDao;
import cn.com.tsjx.goodsCatagory.entity.GoodsCatagory;

@Repository("goodsCatagoryDao")
public class GoodsCatagoryDaoImpl extends BaseDaoImpl<GoodsCatagory, Long> implements GoodsCatagoryDao {

}
