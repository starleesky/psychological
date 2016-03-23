package cn.com.tsjx.infomation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.aspectj.weaver.Dump.INode;
import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.infomation.dao.InfomationDao;
import cn.com.tsjx.infomation.entity.Infomation;
import cn.com.tsjx.infomation.service.InfomationService;
import cn.com.tsjx.user.entity.User;

@Service("infomationService")
public class InfomationServiceImpl extends BaseServiceImpl<Infomation, Long> implements InfomationService {

	@Resource
	private InfomationDao infomationDao;

	@Override
	protected BaseDao<Infomation, Long> getBaseDao() {
		return this.infomationDao;
	}

    @Override
    public List<Infomation> getInfomationsByParam(User user, Infomation infomation) {

        return infomationDao.getInfomationsByParam(user, infomation);
    }
}
