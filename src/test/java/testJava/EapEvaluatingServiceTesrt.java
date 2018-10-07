package testJava;

import cn.com.eap.service.EapEvaluatingService;
import cn.com.eap.web.EvaTypeEnum;
import cn.com.eap.web.dto.EapEvaluatingParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by xin.l on 2018/10/7.
 *
 * @author xin.l
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class EapEvaluatingServiceTesrt {


    @Resource
    EapEvaluatingService eapEvaluatingService;

    @Test
    public void test() {

        EapEvaluatingParam eapEvaluatingParam = new EapEvaluatingParam();

        eapEvaluatingParam.setUserName("李鑫");
        eapEvaluatingParam.setUserPhone("15067196487");
        eapEvaluatingParam.setAge("25");
        eapEvaluatingParam.setSex("1");
        eapEvaluatingParam.setCompany("浙江邵逸夫医院");
        eapEvaluatingParam.setEvaType(EvaTypeEnum.MBTI.getType());
        eapEvaluatingParam.setAnswer("A,B,A,B,A,B,A,B,A,B,A,B,A,B,A,B,A,B,A,B,A,B,A,B,A,B,A,B,A,B,A,B,A,B");
        boolean submit = eapEvaluatingService.submit(eapEvaluatingParam);
        System.out.println(submit);

    }



}
