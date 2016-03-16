package cn.com.tsjx.brand.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.brand.dao.BrandDao;
import cn.com.tsjx.brand.entity.Brand;

@Repository("brandDao")
public class BrandDaoImpl extends BaseDaoImpl<Brand, Long> implements BrandDao {

}
