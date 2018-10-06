package cn.com.tsjx.catagory.dao.impl;

import cn.com.tsjx.common.web.model.Params;
import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.catagory.dao.CatagoryDao;
import cn.com.tsjx.catagory.entity.Catagory;

import java.util.List;

@Repository("catagoryDao")
public class CatagoryDaoImpl extends BaseDaoImpl<Catagory, Long> implements CatagoryDao {

	@Override public List<Catagory> getCatagoryByParentId(String parentId) {
		Params params = Params.create();
		params.add("parentId", parentId);
		return this.selectList(this.getMethodName(), params);
	}

}
