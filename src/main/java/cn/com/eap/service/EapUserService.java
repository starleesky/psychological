package cn.com.eap.service;

import cn.com.eap.entity.EapUser;
import cn.com.tsjx.common.service.BaseService;

/**
 * 用户表服务接口。
 */
public interface EapUserService extends BaseService<EapUser, Long> {


    /**
     * @param eapUser
     * @return
     */
    public Long relevanceUser(EapUser eapUser);
}
