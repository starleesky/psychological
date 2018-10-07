package cn.com.eap.service;

import cn.com.eap.entity.EapEvaluating;
import cn.com.eap.web.dto.EapEvaluatingParam;
import cn.com.tsjx.common.service.BaseService;

/**
 * 评测记录服务接口。
 */
public interface EapEvaluatingService extends BaseService<EapEvaluating, Long> {


    /**
     * 提交答案
     *
     * @param eapEvaluatingParam
     * @return
     */
    public boolean submit(EapEvaluatingParam eapEvaluatingParam);
}
