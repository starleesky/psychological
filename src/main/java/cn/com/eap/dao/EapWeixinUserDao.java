package cn.com.eap.dao;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.eap.entity.EapWeixinUser;

public interface EapWeixinUserDao extends BaseDao<EapWeixinUser, Long> {

    EapWeixinUser findByOpenid(String openid);

}
