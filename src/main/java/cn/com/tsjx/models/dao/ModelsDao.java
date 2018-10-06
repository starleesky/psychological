package cn.com.tsjx.models.dao;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.models.entity.Models;

import java.util.List;

public interface ModelsDao extends BaseDao<Models, Long> {

	public List<Models> listModelsByBrandId(Integer brandId);

}
