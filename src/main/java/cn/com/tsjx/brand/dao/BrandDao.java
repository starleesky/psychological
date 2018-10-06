package cn.com.tsjx.brand.dao;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.brand.entity.Brand;

import java.util.List;

public interface BrandDao extends BaseDao<Brand, Long> {

	public List<Brand> listBrandByCatagoryId(Integer catagoryId);
}
