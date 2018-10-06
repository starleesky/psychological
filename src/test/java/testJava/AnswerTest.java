package testJava;

import cn.com.eap.entity.EapAnswer;
import cn.com.eap.service.EapAnswerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin.l on 2018/10/6.
 *
 * @author xin.l
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class AnswerTest {

    @Resource
    EapAnswerService eapAnswerService;

    private static String answer = "1\tA\tE\t2\t2\n" + "\tB\tI\t2\t2\n" + "2\tA\tS\t2\t2\n" + "\tB\tN\t2\t2\n" + "3\tA\tF\t1\t2\n" + "\tB\tT\t1\t2\n" + "4\tA\tJ\t2\t2\n" + "\tB\tP\t2\t2\n" + "5\tA\tE\t1\t1\n" + "\tB\tI\t2\t2\n" + "6\tA\tN\t2\t2\n" + "\tB\tS\t1\t1\n" + "7\tA\tF\t1\t1\n" + "\tB\tT\t2\t2\n" + "8\tA\tJ\t2\t2\n" + "\tB\tP\t1\t1\n" + "9\tA\tE\t2\t2\n" + "\tB\tI\t2\t2\n" + "10\tA\tS\t2\t2\n" + "\tB\tN\t2\t2\n" + "11\tA\tF\t2\t2\n" + "\tB\tT\t2\t2\n" + "12\tA\tP\t1\t1\n" + "\tB\tJ\t1\t1\n" + "13\tA\tI\t1\t1\n" + "\tB\tE\t2\t2\n" + "14\tA\tS\t1\t1\n" + "\tB\tN\t2\t2\n" + "15\tA\tF\t1\t2\n" + "\tB\tT\t0\t0\n" + "16\tA\tJ\t2\t2\n" + "\tB\tP\t2\t2\n" + "17\tA\tI\t1\t1\n" + "\tB\tE\t2\t2\n" + "18\tA\tN\t1\t1\n" + "\tB\tS\t2\t2\n" + "19\tA\tF\t1\t2\n" + "\tB\tT\t0\t0\n" + "20\tA\tJ\t1\t1\n" + "\tB\tP\t1\t1\n" + "\tC\tP\t1\t1\n" + "21\tA\tE\t2\t2\n" + "\tB\tI\t2\t2\n" + "22\tA\tN\t0\t0\n" + "\tB\tS\t1\t1\n" + "23\tA\tT\t1\t1\n" + "\tB\tF\t0\t0\n" + "24\tA\tP\t1\t1\n" + "\tB\tJ\t0\t0\n" + "\tC\tJ\t1\t1\n" + "25\tA\tE\t1\t1\n" + "\tB\tI\t1\t1\n" + "26\tA\tS\t1\t1\n" + "\tB\tN\t1\t1\n" + "27\tA\tF\t0\t0\n" + "\tB\tT\t2\t1\n" + "28\tA\tJ\t1\t1\n" + "\tB\tP\t2\t2\n" + "29\tA\tE\t1\t1\n" + "\tB\tI\t0\t0\n" + "30\tA\tN\t0\t0\n" + "\tB\tS\t1\t1\n" + "31\tA\tF\t1\t0\n" + "\tB\tT\t2\t2\n" + "32\tA\tJ\t1\t1\n" + "\tB\tP\t1\t1\n" + "33\tA\tE\t1\t1\n" + "\tB\tI\t1\t1\n" + "34\tA\tN\t2\t2\n" + "\tB\tS\t1\t1\n" + "35\tA\tT\t2\t2\n" + "\tB\tF\t2\t1\n" + "36\tA\tP\t1\t1\n" + "\tB\tJ\t1\t1\n" + "37\tA\tI\t1\t1\n" + "\tB\tE\t2\t2\n" + "38\tA\tN\t0\t0\n" + "\tB\tS\t1\t1\n" + "39\tA\tT\t2\t2\n" + "\tB\tF\t2\t1\n" + "40\tA\tI\t0\t0\n" + "\tB\tP\t2\t2\n" + "41\tA\tE\t0\t0\n" + "\tB\tI\t2\t2\n" + "42\tA\tS\t2\t2\n" + "\tB\tN\t0\t0\n" + "43\tA\tT\t1\t2\n" + "\tB\tF\t2\t2\n" + "44\tA\tP\t1\t1\n" + "\tB\tJ\t1\t1\n" + "\tC\tJ\t0\t0\n" + "45\tA\tE\t1\t1\n" + "\tB\tI\t2\t2\n" + "46\tA\tS\t2\t2\n" + "\tB\tN\t1\t1\n" + "47\tA\tT\t1\t1\n" + "\tB\tF\t2\t2\n" + "48\tA\tP\t1\t1\n" + "\tB\tJ\t1\t1\n" + "49\tA\tI\t0\t0\n" + "\tB\tE\t1\t1\n" + "\tC\tI\t2\t2\n" + "50\tA\tS\t2\t2\n" + "\tB\tN\t1\t1\n" + "51\tA\tF\t1\t1\n" + "\tB\tT\t2\t2\n" + "52\tA\tJ\t2\t2\n" + "\tB\tP\t1\t1\n" + "53\tA\tE\t1\t1\n" + "\tB\tI\t1\t1\n" + "54\tA\tN\t2\t2\n" + "\tB\tS\t1\t1\n" + "55\tA\tT\t1\t1\n" + "\tB\tF\t1\t1\n" + "56\tA\tJ\t0\t0\n" + "\tB\tP\t1\t1\n" + "57\tA\tE\t1\t1\n" + "\tB\tI\t2\t2\n" + "58\tA\tS\t1\t1\n" + "\tB\tN\t1\t1\n" + "59\tA\tT\t1\t1\n" + "\tB\tF\t1\t2\n" + "60\tA\tJ\t0\t0\n" + "\tB\tP\t1\t1\n" + "\tC\tP\t1\t1\n" + "61\tA\tE\t1\t1\n" + "\tB\tI\t2\t2\n" + "62\tA\tN\t0\t0\n" + "\tB\tS\t2\t2\n" + "63\tA\tT\t2\t2\n" + "\tB\tF\t0\t0\n" + "64\tA\tJ\t1\t1\n" + "\tB\tP\t0\t0\n" + "65\tA\tI\t1\t1\n" + "\tB\tE\t0\t0\n" + "66\tA\tS\t2\t2\n" + "\tB\tN\t0\t0\n" + "67\tA\tF\t0\t0\n" + "\tB\tT\t2\t2\n" + "68\tA\tJ\t2\t2\n" + "\tB\tP\t2\t2\n" + "69\tA\tI\t1\t1\n" + "\tB\tE\t1\t1\n" + "70\tA\tS\t2\t2\n" + "\tB\tN\t0\t0\n" + "71\tA\tF\t0\t0\n" + "\tB\tT\t2\t2\n" + "72\tA\tJ\t2\t2\n" + "\tB\tP\t2\t2\n" + "73\tA\tE\t0\t0\n" + "\tB\tI\t1\t1\n" + "74\tA\tS\t1\t1\n" + "\tB\tN\t0\t0\n" + "75\tA\tF\t0\t0\n" + "\tB\tT\t2\t2\n" + "76\tA\tJ\t2\t2\n" + "\tB\tP\t2\t2\n" + "77\tA\tE\t1\t1\n" + "\tB\tI\t1\t1\n" + "78\tA\tS\t1\t1\n" + "\tB\tN\t2\t2\n" + "79\tA\tF\t0\t0\n" + "\tB\tT\t1\t1\n" + "80\tA\tP\t2\t2\n" + "\tB\tJ\t1\t1\n" + "81\tA\tE\t1\t1\n" + "\tB\tI\t0\t0\n" + "82\tA\tS\t2\t2\n" + "\tB\tN\t1\t1\n" + "83\tA\tF\t1\t1\n" + "\tB\tT\t1\t1\n" + "84\tA\tJ\t1\t1\n" + "\tB\tP\t1\t1\n" + "85\tA\tS\t0\t0\n" + "\tB\tN\t2\t2\n" + "86\tA\tI\t2\t2\n" + "\tB\tF\t0\t0\n" + "87\tA\tP\t0\t0\n" + "\tB\tJ\t1\t1\n" + "88\tA\tN\t2\t2\n" + "\tB\tS\t0\t0\n" + "89\tA\tF\t0\t1\n" + "\tB\tT\t0\t0\n" + "90\tA\tJ\t2\t2\n" + "\tB\tP\t1\t1\n" + "91\tA\tS\t1\t1\n" + "\tB\tN\t0\t0\n" + "92\tA\tP\t1\t1\n" + "\tB\tJ\t0\t0\n" + "93\tA\tS\t1\t1\n" + "\tB\tN\t0\t0\n" + "94\tA\tS\t1\t1\n" + "\tB\tN\t1\t1\n";

    @Test
    public void test() throws InvocationTargetException, IllegalAccessException {
        List<EapAnswer> eapAnswers = new ArrayList<>();
        String[] split = answer.split("\n");
        String num = "1";

        for (String s : split) {
            EapAnswer eapAnswer = new EapAnswer();
            String[] split1 = s.split("\t");
            if (split1.length == 5) {
                if (!"".equals(split1[0])) {
                    num = split1[0];
                }
                eapAnswer.setNum(num);
                eapAnswer.setEvaType("1");
                eapAnswer.setOptions(split1[1]);
                eapAnswer.setDimension(split1[2]);
                eapAnswer.setScore(split1[3]);
                eapAnswer.setSex("1");
                eapAnswers.add(eapAnswer);

                EapAnswer eapAnswer2 = new EapAnswer();
                BeanUtils.copyProperties(eapAnswer, eapAnswer2);
                eapAnswer2.setScore(split1[4]);
                eapAnswer2.setSex("2");
                eapAnswers.add(eapAnswer2);


            } else {
                eapAnswer.setNum(num);
                eapAnswer.setEvaType("1");
                eapAnswer.setOptions(split1[0]);
                eapAnswer.setDimension(split1[1]);
                eapAnswer.setScore(split1[2]);
                eapAnswer.setSex("1");
                eapAnswers.add(eapAnswer);

                EapAnswer eapAnswer2 = new EapAnswer();
                BeanUtils.copyProperties(eapAnswer, eapAnswer2);
                eapAnswer2.setScore(split1[3]);
                eapAnswer2.setSex("2");
                eapAnswers.add(eapAnswer2);


            }

        }

        System.out.println(eapAnswers.size());

        for (EapAnswer eapAnswer : eapAnswers) {
            eapAnswerService.insert(eapAnswer);

        }

    }
}
