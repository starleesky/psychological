package cn.com.eap.service;

import cn.com.tsjx.common.service.BaseService;
import cn.com.eap.entity.EapSubscribe;

/**
 * 预约记录服务接口。
 */
public interface EapSubscribeService extends BaseService<EapSubscribe, Long> {


    /**
     * 用户提交预约
     * @param eapSubscribe
     * @return
     */
    public boolean sumbit(EapSubscribe eapSubscribe);

}
