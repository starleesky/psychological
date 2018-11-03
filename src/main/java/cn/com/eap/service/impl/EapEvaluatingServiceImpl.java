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
        eapEvaluatingParam.setResult(stringBuffer.toString());
        eapEvaluatingParam.setScore(stringBuffer.toString());
        eapEvaluatingParam.setEvaName(EvaTypeEnum.getDiscribeByType(eapEvaluatingParam.getEvaType()));

        eapEvaluatingDao.insert(eapEvaluatingParam);

        return true;
    }
}
