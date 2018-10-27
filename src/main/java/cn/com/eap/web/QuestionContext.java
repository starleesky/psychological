package cn.com.eap.web;

import cn.com.eap.web.dto.QuestionDto;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xin.l on 2018/10/6.
 *
 * @author xin.l
 */

@Component
public class QuestionContext implements InitializingBean {


    private Logger log = LoggerFactory.getLogger(QuestionContext.class);


    public final static Map<String, QuestionDto> QUESTION_DTO_MAP = new ConcurrentHashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        InputStream resource = getClass().getClassLoader().getResourceAsStream("mbti.json");
        String jsonString = FileUtil.ReadFile(resource);
        QuestionDto questionDto = JSON.parseObject(jsonString, QuestionDto.class);
        QUESTION_DTO_MAP.put(EvaTypeEnum.MBTI.code(), questionDto);

        resource = getClass().getClassLoader().getResourceAsStream("oq45.json");
        jsonString = FileUtil.ReadFile(resource);
        questionDto = JSON.parseObject(jsonString, QuestionDto.class);
        QUESTION_DTO_MAP.put(EvaTypeEnum.OQ45.code(), questionDto);

        resource = getClass().getClassLoader().getResourceAsStream("scl90.json");
        jsonString = FileUtil.ReadFile(resource);
        questionDto = JSON.parseObject(jsonString, QuestionDto.class);
        QUESTION_DTO_MAP.put(EvaTypeEnum.SCL90.code(), questionDto);


        log.info("评测题目初始化成功");


    }
}
