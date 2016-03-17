package cn.com.tsjx.user.dao;

import java.util.List;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.user.entity.User;

public interface UserDao extends BaseDao<User, Long> {

    public List<User> getUsersByCity(String city);
}
