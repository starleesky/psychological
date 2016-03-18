package cn.com.tsjx.models.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.models.dao.ModelsDao;
import cn.com.tsjx.models.entity.Models;

@Repository("modelsDao")
public class ModelsDaoImpl extends BaseDaoImpl<Models, Long> implements ModelsDao {

}
