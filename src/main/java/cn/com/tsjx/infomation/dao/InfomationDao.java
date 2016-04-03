package cn.com.tsjx.infomation.dao;

import java.util.List;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.common.web.model.Params;
import cn.com.tsjx.infomation.entity.Infomation;
import cn.com.tsjx.infomation.entity.InfomationDto;
import cn.com.tsjx.user.entity.User;

public interface InfomationDao extends BaseDao<Infomation, Long> {

    public List<Infomation> getInfomationsByParam(User user, Infomation infomation);
    
    public Pager<InfomationDto> getPagerCollections(Params map, Pager<InfomationDto> pager);
    
    public Pager<InfomationDto> getInfoPagerWithImg(Params map, Pager<InfomationDto> pager);
}
