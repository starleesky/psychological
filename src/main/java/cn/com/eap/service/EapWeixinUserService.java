package cn.com.eap.service;

import cn.com.eap.entity.EapWeixinUser;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.service.BaseService;

/**
 * 服务接口。
 */
public interface EapWeixinUserService extends BaseService<EapWeixinUser, Long> {

    Result<String> weiXinLogin(String code);
}
