package cn.com.eap.service.impl;

import cn.com.eap.dao.EapWeixinUserDao;
import cn.com.eap.entity.EapWeixinUser;
import cn.com.eap.service.EapWeixinUserService;
import cn.com.eap.utils.WeiXinUtil;
import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.service.BaseServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("eapWeixinUserService")
public class EapWeixinUserServiceImpl extends BaseServiceImpl<EapWeixinUser, Long> implements EapWeixinUserService {

	@Resource
	private EapWeixinUserDao eapWeixinUserDao;

	@Override
	protected BaseDao<EapWeixinUser, Long> getBaseDao() {
		return this.eapWeixinUserDao;
	}

    @Override
    public Result<String> weiXinLogin(String code) {
        Result<String> result = new Result<>();
        String codeResult = WeiXinUtil.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx8013f15c4f44d007&secret=72dbfae8149f85b588135469e6c44462&code="+code+"&grant_type=authorization_code");
        if (codeResult != null){
            JSONObject jsonObject = JSONObject.parseObject(codeResult);
            String accessToken = jsonObject.getString("access_token");
            String openid = jsonObject.getString("openid");
            if (accessToken != null){
                String user = WeiXinUtil.get("https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openid);
                JSONObject userObject = JSONObject.parseObject(user);
                userObject.getString("");
                EapWeixinUser eapWeixinUser = new EapWeixinUser();
                eapWeixinUser.setOpenid(openid);
                List<EapWeixinUser> temp = eapWeixinUserDao.find(eapWeixinUser);
                if (temp != null && temp.size() == 1){
                    EapWeixinUser weixinUser = temp.get(0);
                    weixinUser.setSex(userObject.getString("sex"));
                    weixinUser.setNickName(userObject.getString("nickname"));
                    weixinUser.setIcon(userObject.getString("headimgurl"));
                    weixinUser.setCity(userObject.getString("city"));
                    eapWeixinUserDao.update(weixinUser);
                }else{
                    eapWeixinUser.setSex(userObject.getString("sex"));
                    eapWeixinUser.setNickName(userObject.getString("nickname"));
                    eapWeixinUser.setIcon(userObject.getString("headimgurl"));
                    eapWeixinUser.setCity(userObject.getString("city"));
                    eapWeixinUserDao.insert(eapWeixinUser);
                }
                result.setResult(true);
                result.setMessage("成功");
                return result;
            }
        }
        result.setResult(false);
        result.setMessage("登录失败");
        return result;
    }
}
