package cn.com.eap.service.impl;

import cn.com.eap.dao.EapUserDao;
import cn.com.eap.entity.EapUser;
import cn.com.eap.service.EapUserService;
import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("eapUserService")
public class EapUserServiceImpl extends BaseServiceImpl<EapUser, Long> implements EapUserService {

    @Resource
    private EapUserDao eapUserDao;

    @Override
    protected BaseDao<EapUser, Long> getBaseDao() {
        return this.eapUserDao;
    }

    @Override
    public Long relevanceUser(EapUser eapUser) {

        EapUser user = new EapUser();
        user.setUserPhone(eapUser.getUserPhone());
        List<EapUser> eapUsers = eapUserDao.find(user);
        if (CollectionUtils.isEmpty(eapUsers)) {
            eapUserDao.insert(eapUser);
            return eapUser.getId();
        } else {
            EapUser resultUser = eapUsers.get(0);
            if (eapUser.getEvaluatingCount() != null) {
                resultUser.setEvaluatingCount(resultUser.getEvaluatingCount() + eapUser.getEvaluatingCount());
            } else if (eapUser.getSubscribeCount() != null) {
                resultUser.setSubscribeCount(resultUser.getSubscribeCount() + eapUser.getSubscribeCount());
            }
            eapUserDao.update(resultUser);
            return resultUser.getId();
        }

    }
}
