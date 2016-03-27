package cn.com.tsjx.infomation.service;

import java.util.List;
import java.util.Map;

import cn.com.tsjx.common.service.BaseService;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.common.web.model.Params;
import cn.com.tsjx.infomation.entity.Infomation;
import cn.com.tsjx.user.entity.User;

/**
 *  信息内容表服务接口。
 */
public interface InfomationService extends BaseService<Infomation, Long> {

    /**
     * 根据用户和信息查询信息集合
     * @param user 用户信息
     * @param infomation 信息类
     * @return 
     */
    public List<Infomation> getInfomationsByParam(User user,Infomation infomation);
    
    /**
     * 分页查询收藏
     * @param map
     * @param pager
     * @return
     */
    public Pager<Infomation> getPagerCollections(Params map,Pager<Infomation> pager);
}
