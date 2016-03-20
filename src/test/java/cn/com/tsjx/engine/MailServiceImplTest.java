package cn.com.tsjx.engine;

import javax.annotation.Resource;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import cn.com.tsjx.sys.MailService;

/**
 * @Type MailServiceImplTest
 * @Desc 
 * @author hefan
 * @date 2015年11月6日
 * @Version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class MailServiceImplTest {
    
    @Resource(name = "mailService")
    private MailService mailService;

    @Test
    public void testIsMailConfigComplete() {
        //fail("Not yet implemented");
    }
    
    @Test
    public void testSendMailTest() {
        mailService.sendMailTest("test@chuanjinet.com", "smtp.exmail.qq.com", 25, "test@chuanjinet.com", "Test123", "821866756@qq.com");
    }

    
    @Test
    public void testSendMail() {
        mailService.sendMail("821866756@qq.com", "测试", "123", "赣游通");
    }

}
