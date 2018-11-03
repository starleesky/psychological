package cn.com.eap.web.dto;

import cn.com.eap.entity.EapEvaluating;

/**
 * Created by xin.l on 2018/10/7.
 *
 * @author xin.l
 */
public class EapEvaluatingParam extends EapEvaluating {

    private String answer;

    private String ids;

    private String[] idArray;

    public String[] getIdArray() {
        return idArray;
    }

    public void setIdArray(String[] idArray) {
        this.idArray = idArray;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
