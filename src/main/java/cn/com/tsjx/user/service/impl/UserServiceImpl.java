package cn.com.tsjx.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.user.dao.UserDao;
import cn.com.tsjx.user.entity.User;
import cn.com.tsjx.user.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	protected BaseDao<User, Long> getBaseDao() {
		return this.userDao;
	}

    @Override
    public List<User> getUsersByCity(String city) {
        return userDao.getUsersByCity(city);
    }
}
