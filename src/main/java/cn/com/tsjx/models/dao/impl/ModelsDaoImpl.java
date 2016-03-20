package cn.com.tsjx.models.dao.impl;

import cn.com.tsjx.common.web.model.Params;
import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.models.dao.ModelsDao;
import cn.com.tsjx.models.entity.Models;

import java.util.List;

@Repository("modelsDao")
public class ModelsDaoImpl extends BaseDaoImpl<Models, Long> implements ModelsDao {

	@Override public List<Models> listModelsByBrandId(Integer brandId) {
		Params params = Params.create();
		params.add("brandId", brandId);
		return this.selectList(this.getMethodName(), params);
	}

}
