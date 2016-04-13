package cn.com.tsjx.infomation.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.common.enums.Deleted;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.common.web.model.Params;
import cn.com.tsjx.infomation.dao.InfomationDao;
import cn.com.tsjx.infomation.entity.Infomation;
import cn.com.tsjx.infomation.entity.InfomationDto;
import cn.com.tsjx.user.entity.User;

@Repository("infomationDao")
public class InfomationDaoImpl extends BaseDaoImpl<Infomation, Long> implements InfomationDao {

    @Override
    public List<Infomation> getInfomationsByParam(User user, Infomation infomation) {
        Params params = Params.create();
        params.add("deleted", Deleted.NO.value);
        params.add("userId", user.getId());
        return this.selectList(this.getMethodName(), params);
    }

    @Override
    public Pager<InfomationDto> getPagerCollections(Params map, Pager<InfomationDto> pager) {
        
        return this.selectPage(this.getMethodName(),map,pager);
        
    }
    
    public Pager<InfomationDto> getInfoPagerWithImg(Params map, Pager<InfomationDto> pager) {
    	
    	return this.selectPage(this.getMethodName(),map,pager);
    }
    
    public Pager<InfomationDto> getInfoPagerWithImgNoUser(Params map, Pager<InfomationDto> pager) {
    	
    	return this.selectPage(this.getMethodName(),map,pager);
    }
    
    public void autoDown() {
    	Params param = Params.create();
    	this.update(this.getMethodName(),param);
    }
    
    
}
