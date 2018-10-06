package cn.com.tsjx.common.web.baidu;

import java.util.List;

/**
 * @Type BaiduSuggestion
 * @Desc 
 * @author hefan
 * @date 2015年10月24日
 * @Version V1.0
 */
public class Suggestion {

    private Integer status;
    private String message;
    private List<SuggestionResult> result;
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<SuggestionResult> getResult() {
        return result;
    }
    public void setResult(List<SuggestionResult> result) {
        this.result = result;
    }
    
    
}
