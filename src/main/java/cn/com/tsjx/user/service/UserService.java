package cn.com.tsjx.user.service;

import java.util.List;

import cn.com.tsjx.common.service.BaseService;
import cn.com.tsjx.user.entity.User;

/**
 * 用户表服务接口。
 */
public interface UserService extends BaseService<User, Long> {

    /**
     * 通过邮箱来查询用户
     * @param city
     * @return
     */
    public List<User> getUsersByEmail(String email);
}
