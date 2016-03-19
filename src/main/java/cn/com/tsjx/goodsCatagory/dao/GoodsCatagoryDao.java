package cn.com.tsjx.goodsCatagory.dao;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.goodsCatagory.entity.GoodsCatagory;
import cn.com.tsjx.user.entity.User;

import java.util.List;

public interface GoodsCatagoryDao extends BaseDao<GoodsCatagory, Long> {

	public List<GoodsCatagory> getGoodsCatagoryByParentId(String parentId);
}
