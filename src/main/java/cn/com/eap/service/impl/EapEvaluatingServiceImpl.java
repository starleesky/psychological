package cn.com.eap.service.impl;

import cn.com.eap.dao.EapEvaluatingDao;
import cn.com.eap.entity.EapAnswer;
import cn.com.eap.entity.EapEvaluating;
import cn.com.eap.entity.EapUser;
import cn.com.eap.service.EapAnswerService;
import cn.com.eap.service.EapEvaluatingService;
import cn.com.eap.service.EapUserService;
import cn.com.eap.web.EvaTypeEnum;
import cn.com.eap.web.dto.EapEvaluatingParam;
import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("eapEvaluatingService")
public class EapEvaluatingServiceImpl extends BaseServiceImpl<EapEvaluating, Long> implements EapEvaluatingService {

    @Resource
    private EapEvaluatingDao eapEvaluatingDao;

    @Resource
    EapUserService eapUserService;

    @Resource
    EapAnswerService eapAnswerService;

    private Logger log = LoggerFactory.getLogger(EapEvaluatingServiceImpl.class);


    @Override
    protected BaseDao<EapEvaluating, Long> getBaseDao() {
        return this.eapEvaluatingDao;
    }

    @Override
    public boolean submit(EapEvaluatingParam eapEvaluatingParam) {

        log.info("eap evaluating param:{}", JSON.toJSONString(eapEvaluatingParam));

        EapUser eapUser = new EapUser();
        BeanUtils.copyProperties(eapEvaluatingParam, eapUser);
        eapUser.setEvaluatingCount(1);
        eapUser.setSubscribeCount(0);

        eapUser.setSrc("1"); //评测

        Long userId = eapUserService.relevanceUser(eapUser);
        eapEvaluatingParam.setUserId(userId);
        EapAnswer eapAnswer = new EapAnswer();
        if (EvaTypeEnum.MBTI.getType().equals(eapEvaluatingParam.getEvaType())) {
            eapAnswer.setSex(eapEvaluatingParam.getSex());
        } else {
            eapAnswer.setSex("0");//未知
        }
        eapAnswer.setEvaType(eapEvaluatingParam.getEvaType());
        List<EapAnswer> eapAnswers = eapAnswerService.find(eapAnswer);
        if (CollectionUtils.isEmpty(eapAnswers)) {
            System.out.println("题目答案为空");
            return false;
        }

        Map<String, EapAnswer> collect = new HashMap<>();
        for (EapAnswer answer : eapAnswers) {
            collect.put(answer.getNum() + "_" + answer.getOptions(), answer);
        }

        Map<String, Integer> dimension = new HashMap<>();
        String[] split = eapEvaluatingParam.getAnswer().split(",");
        int num = 1;
        for (String answer : split) {
            EapAnswer eapAnswerResult = collect.get(num + "_" + answer);
            if (eapAnswerResult == null) {
                System.out.println("题目答案不存在");
                return false;
            }

            if (dimension.get(eapAnswerResult.getDimension()) == null) {
                dimension.put(eapAnswerResult.getDimension(), Integer.parseInt(eapAnswerResult.getScore()));
            } else {
                Integer score = dimension.get(eapAnswerResult.getDimension());
                score = score + Integer.parseInt(eapAnswerResult.getScore());
                dimension.put(eapAnswerResult.getDimension(), score);
            }
            num++;
        }
        Iterator<Map.Entry<String, Integer>> iterator = dimension.entrySet().iterator();
        StringBuffer stringBuffer = new StringBuffer();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            stringBuffer.append(next.getKey()).append(":").append(next.getValue()).append(",");
        }
        eapEvaluatingParam.setResult(getResult(dimension,eapEvaluatingParam.getEvaType()));
        eapEvaluatingParam.setScore(stringBuffer.toString());
        eapEvaluatingParam.setEvaName(EvaTypeEnum.getDiscribeByType(eapEvaluatingParam.getEvaType()));

        eapEvaluatingDao.insert(eapEvaluatingParam);

        return true;
    }

    private String getResult(Map<String, Integer> dimension,String evaType){

        String result = "";
        if(EvaTypeEnum.MBTI.getType().equals(evaType)){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("E").append(dimension.get("E")).append(",");
;           stringBuffer.append("I").append(dimension.get("I")).append(",");
            stringBuffer.append("S").append(dimension.get("S")).append(",");
            stringBuffer.append("N").append(dimension.get("N")).append(",");
            stringBuffer.append("T").append(dimension.get("T")).append(",");
            stringBuffer.append("F").append(dimension.get("F")).append(",");
            stringBuffer.append("J").append(dimension.get("J")).append(",");
            stringBuffer.append("P").append(dimension.get("P")).append("\n");
            stringBuffer.append("结果倾向：");

            stringBuffer.append(dimension.getOrDefault("E",0)>dimension.getOrDefault("I",0)?"E":"I");
            stringBuffer.append(dimension.getOrDefault("S",0)>dimension.getOrDefault("N",0)?"S":"N");
            stringBuffer.append(dimension.getOrDefault("T",0)>dimension.getOrDefault("F",0)?"T":"F");
            stringBuffer.append(dimension.getOrDefault("J",0)>dimension.getOrDefault("P",0)?"J":"P");

            result = stringBuffer.toString();
        } else if(EvaTypeEnum.OQ45.getType().equals(evaType)){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("情  绪").append(dimension.get("情  绪")).append(",");
            stringBuffer.append("人际关系").append(dimension.get("人际关系")).append(",");
            stringBuffer.append("社会功能").append(dimension.get("社会功能")).append("\n");
            stringBuffer.append("总分").append(dimension.get("社会功能") + dimension.get("人际关系") + dimension.get("情  绪"));
            result = stringBuffer.toString();
        }
        else if(EvaTypeEnum.SCL90.getType().equals(evaType)){
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append("躯体化").append(new BigDecimal(dimension.getOrDefault("躯体化",0)).divide(new BigDecimal("12"),2,   BigDecimal.ROUND_HALF_UP).doubleValue()).append(",");
            stringBuffer.append("强   迫").append(new BigDecimal(dimension.getOrDefault("强   迫",0)).divide(new BigDecimal("10"),2,   BigDecimal.ROUND_HALF_UP).doubleValue()).append(",");
            stringBuffer.append("人际敏感").append(new BigDecimal(dimension.getOrDefault("人际敏感",0)).divide(new BigDecimal("9"),2,   BigDecimal.ROUND_HALF_UP).doubleValue()).append(",");
            stringBuffer.append("抑   郁").append(new BigDecimal(dimension.getOrDefault("抑   郁",0)).divide(new BigDecimal("13"),2,   BigDecimal.ROUND_HALF_UP).doubleValue()).append(",");
            stringBuffer.append("焦   虑").append(new BigDecimal(dimension.getOrDefault("焦   虑",0)).divide(new BigDecimal("10"),2,   BigDecimal.ROUND_HALF_UP).doubleValue()).append(",");
            stringBuffer.append("敌   对").append(new BigDecimal(dimension.getOrDefault("焦   虑",0)).divide(new BigDecimal("10"),2,   BigDecimal.ROUND_HALF_UP).doubleValue()).append(",");
            stringBuffer.append("恐   怖").append(new BigDecimal(dimension.getOrDefault("恐   怖",0)).divide(new BigDecimal("7"),2,   BigDecimal.ROUND_HALF_UP).doubleValue()).append(",");
            stringBuffer.append("偏   执").append(new BigDecimal(dimension.getOrDefault("偏   执",0)).divide(new BigDecimal("6"),2,   BigDecimal.ROUND_HALF_UP).doubleValue()).append(",");
            stringBuffer.append("精神病" ).append(new BigDecimal(dimension.getOrDefault("精神病",0)).divide(new BigDecimal("10"),2,   BigDecimal.ROUND_HALF_UP).doubleValue()).append(",");
            stringBuffer.append("其   他").append(new BigDecimal(dimension.getOrDefault("其   他",0)).divide(new BigDecimal("7"),2,   BigDecimal.ROUND_HALF_UP).doubleValue()).append("\n");
            Integer num =
                    dimension.getOrDefault("躯体化", 0) +
                    dimension.getOrDefault("强   迫", 0) +
                    dimension.getOrDefault("人际敏感", 0) +
                    dimension.getOrDefault("抑   郁", 0) +
                    dimension.getOrDefault("焦   虑", 0) +
                    dimension.getOrDefault("敌   对", 0) +
                    dimension.getOrDefault("恐   怖", 0) +
                    dimension.getOrDefault("偏   执", 0) +
                    dimension.getOrDefault("精神病", 0) +
                    dimension.getOrDefault("其   他", 0);
            stringBuffer.append("总均分：").append(new BigDecimal(num).divide(new BigDecimal("90"), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
            result = stringBuffer.toString();
        }
        return result;

    }


}
