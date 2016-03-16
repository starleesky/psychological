package cn.com.tsjx.brand.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.brand.dao.BrandDao;
import cn.com.tsjx.brand.entity.Brand;
import cn.com.tsjx.brand.service.BrandService;

@Service("brandService")
public class BrandServiceImpl extends BaseServiceImpl<Brand, Long> implements BrandService {

	@Resource
	private BrandDao brandDao;

	@Override
	protected BaseDao<Brand, Long> getBaseDao() {
		return this.brandDao;
	}
}
