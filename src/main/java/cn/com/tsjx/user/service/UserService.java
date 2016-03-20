package cn.com.tsjx.user.service;

import cn.com.tsjx.common.service.BaseService;
import cn.com.tsjx.user.entity.User;

/**
 * 用户表服务接口。
 */
public interface UserService extends BaseService<User, Long> {

    /**
     * 通过用户名来查询用户
     * 用户可以通过用户名或手机或邮箱 加密码的方式登录
     * @param 
     * @return
     */
    public User getUsersByParam(String userName,String password);
}
