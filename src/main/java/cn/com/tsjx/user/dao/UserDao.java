package cn.com.tsjx.user.dao;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.user.entity.User;

public interface UserDao extends BaseDao<User, Long> {

    public User getUsersByParam(String userName, String password);

    public void updateMsgAll();
    
    public void autoDeteleUser();
}
