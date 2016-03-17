package cn.com.tsjx.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.common.web.model.Params;
import cn.com.tsjx.user.dao.UserDao;
import cn.com.tsjx.user.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    @Override
    public List<User> getUsersByEmail(String email) {
        Params params = Params.create();
        params.add("email",email);
        return this.selectList(this.getMethodName(), params);
    }

}
