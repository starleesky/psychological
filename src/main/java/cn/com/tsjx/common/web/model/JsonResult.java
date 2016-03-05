package cn.com.tsjx.common.web.model;

import java.io.Serializable;

/**
 * 返回Json对应的VO
 * 
 * @Type JsonResult
 * @Desc
 * @author tianzhonghong
 * @date 2015年5月8日
 * @Version V1.0
 */
public class JsonResult implements Serializable {

    private static final long serialVersionUID = -3241879950189554036L;


    private Boolean success;
    private String message;
    private Object data;


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
