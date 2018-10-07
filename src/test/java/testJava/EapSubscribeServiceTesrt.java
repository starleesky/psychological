package testJava;

import cn.com.eap.entity.EapSubscribe;
import cn.com.eap.service.EapSubscribeService;
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
public class EapSubscribeServiceTesrt {


    @Resource
    EapSubscribeService eapSubscribeService;

    @Test
    public void test() {
        EapSubscribe eapSubscribe = new EapSubscribe();
        eapSubscribe.setUserName("李鑫");
        eapSubscribe.setUserPhone("15067196487");
        eapSubscribe.setAge("25");
        eapSubscribe.setSex("0");
        eapSubscribe.setRemark("wo沃尔沃而无恶日文家居服问我我诶几我二姐我二姐我IE兼容乌尔维尔忘记哦为我额哦我为日文就饿哦人我 我二哦加我而我家金额为日文而加夫里什肌肤都 is 暖风机哦加微哦加我交流技术开发地位妇女违法乱纪联赛积分 i");
        eapSubscribe.setCompany("浙江邵逸夫医院");

        boolean sumbit = eapSubscribeService.sumbit(eapSubscribe);
        System.out.println(sumbit);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
