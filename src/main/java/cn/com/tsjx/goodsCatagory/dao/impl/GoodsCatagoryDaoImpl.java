package cn.com.tsjx.goodsCatagory.dao.impl;

import cn.com.tsjx.common.web.model.Params;
import org.springframework.stereotype.Repository;

import com.hp.hpl.sparta.xpath.ThisNodeTest;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.goodsCatagory.dao.GoodsCatagoryDao;
import cn.com.tsjx.goodsCatagory.entity.GoodsCatagory;

import java.util.List;

@Repository("goodsCatagoryDao")
public class GoodsCatagoryDaoImpl extends BaseDaoImpl<GoodsCatagory, Long> implements GoodsCatagoryDao {

	@Override public List<GoodsCatagory> getGoodsCatagoryByParentId(String parentId) {
		Params params = Params.create();
		params.add("parentId", parentId);
		return this.selectList(this.getMethodName(), params);
	}
}
