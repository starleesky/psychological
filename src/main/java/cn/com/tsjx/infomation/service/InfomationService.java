package cn.com.tsjx.infomation.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import cn.com.tsjx.common.service.BaseService;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.common.web.model.Params;
import cn.com.tsjx.infomation.entity.Infomation;
import cn.com.tsjx.infomation.entity.InfomationDto;
import cn.com.tsjx.user.entity.User;

/**
 * 信息内容表服务接口。
 */
public interface InfomationService extends BaseService<Infomation, Long> {

    /**
     * 根据用户和信息查询信息集合
     *
     * @param user       用户信息
     * @param infomation 信息类
     * @return
     */
    public List<Infomation> getInfomationsByParam(User user, Infomation infomation);

    /**
     * 分页查询收藏
     *
     * @param map
     * @param pager
     * @return
     */
    public Pager<InfomationDto> getPagerCollections(Params map, Pager<InfomationDto> pager);

    /**
     * 分页查询信息列表（带图片）
     *
     * @param map
     * @param pager
     * @param relUser 是否关联用户ID查询
     * @return
     */
    public Pager<InfomationDto> getInfoPagerWithImg(Params map, Pager<InfomationDto> pager, Boolean relUser);


    /**
     * 导入信息数据
     *
     * @param inputStream
     */
    public void bathInfomation(InputStream inputStream) throws IOException;
}
