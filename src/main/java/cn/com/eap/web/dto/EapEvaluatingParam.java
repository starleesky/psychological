package cn.com.eap.web.dto;

import cn.com.eap.entity.EapEvaluating;

/**
 * Created by xin.l on 2018/10/7.
 *
 * @author xin.l
 */
public class EapEvaluatingParam extends EapEvaluating {

    private String answer;

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
