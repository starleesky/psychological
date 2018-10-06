package cn.com.tsjx.catagory.dao;

import cn.com.tsjx.catagory.entity.Catagory;
import cn.com.tsjx.common.dao.BaseDao;

import java.util.List;

public interface CatagoryDao extends BaseDao<Catagory, Long> {

	public List<Catagory> getCatagoryByParentId(String parentId);
}
