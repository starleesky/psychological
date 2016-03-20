package cn.com.tsjx.brand.dao.impl;

import cn.com.tsjx.common.web.model.Params;
import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.brand.dao.BrandDao;
import cn.com.tsjx.brand.entity.Brand;

import java.util.List;

@Repository("brandDao")
public class BrandDaoImpl extends BaseDaoImpl<Brand, Long> implements BrandDao {

	@Override public List<Brand> listBrandByCatagoryId(Integer catagoryId) {
		Params params = Params.create();
		params.add("catagoryId", catagoryId);
		return this.selectList(this.getMethodName(), params);
	}

}
