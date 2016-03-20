package cn.com.tsjx.user.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.common.enums.Deleted;
import cn.com.tsjx.common.web.model.Params;
import cn.com.tsjx.user.dao.UserDao;
import cn.com.tsjx.user.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    @Override
    public User getUsersByParam(String userName, String password) {
        Params params = Params.create();
        params.add("userName",userName);
        params.add("password",password);
        params.add("deleted", Deleted.NO.value);
        return this.selectOne(this.getMethodName(), params);
    }

}
