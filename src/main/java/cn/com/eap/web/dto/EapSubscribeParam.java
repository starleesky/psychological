package cn.com.eap.web.dto;

import cn.com.eap.entity.EapSubscribe;

/**
 * Created by xin.l on 2018/10/6.
 *
 * @author xin.l
 */
public class EapSubscribeParam extends EapSubscribe {

    private String code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
