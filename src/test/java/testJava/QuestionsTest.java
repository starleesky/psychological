package testJava;

import cn.com.eap.web.dto.QuestionDto;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin.l on 2018/10/6.
 *
 * @author xin.l
 */
public class QuestionsTest {

    private final static String question = "1．我通常\n" + "A. 善于跟别人交往\n" + "B. 沉默寡语\n" + "\n" + "2．如果我是老师，我想教\n" + "A. 实用性强的科目\n" + "B. 理论性强的科目\n" + "\n" + "3．我经常\n" + "A. 感性驾驭理性\n" + "B. 理性驾驭感性\n" + "\n" + "4．出门之前，我通常\n" + "A. 事先做好日程安排\n" + "B. 无计划出门\n" + "\n" + "5．与一群人在一起时，我通常\n" + "A. 与很多人进行交流\n" + "B. 找熟悉的人私下交流\n" + "\n" + "6．我更喜欢与\n" + "A. 想象力丰富的人交流\n" + "B. 有现实感的人交流\n" + "\n" + "7．对我来说更好的评价是\n" + "A. 性情中人 \n" + "B. 合理的人 \n" + "\n" + "8．由我来筹备聚会时，我通常\n" + "A. 事先制定计划，并按照计划进行\n" + "B. 根据情况自由进行 \n" + "\n" + "9．与陌生人时见面时，通常是\n" + "A. 我来介绍其他人\n" + "B. 由别人来介绍我 \n" + "\n" + "10．我想得到他人认可的部分是\n" + "A. 务实、有现实感 \n" + "B. 有创意，有独创精神 \n" + "\n" + "11．我通常\n" + "A. 注重感性甚于理性 \n" + "B. 注重理性甚于感性 \n" + "\n" + "12．在以下情况下我能更好地完成事情\n" + "A. 做突如其来的事情或需要迅速做的事情\n" + "B. 按照精细的计划做事情\n" + "\n" + "13．我通常\n" + "A. 与少数人有较深的交往 \n" + "B. 与各种各样的人进行交流 \n" + "\n" + "14．我更尊重\n" + "A. 较保守，不轻易被人了解的人\n" + "B. 独创一格、有个性，不在乎被人了解的人\n" + "\n" + "15．以下两点中我认为更糟的是\n" + "A. 缺乏同情心 \n" + "B. 不合理 \n" + "\n" + "16．按日程表行事\n" + "A. 让我感到很舒服 \n" + "B. 让我感到很不自在 \n" + "\n" + "17．我在朋友当中\n" + "A. 信息较为闭塞\n" + "B. 信息比较灵通\n" + "\n" + "18．我想交的朋友是\n" + "A. 不断有新创意的人 \n" + "B. 有现实感的人 \n" + "\n" + "19．我更喜欢一起共事的人是\n" + "A. 一向亲切的人 \n" + "B. 一向公正的人 \n" + "\n" + "20．对周末要做的事情做日程安排，对我来说\n" + "A. 比较得心应手\n" + "B. 不太愿意做这种事情 \n" + "C. 想到此事心里就沉甸甸\n" + "\n" + "21．通常我\n" + "A. 能够与任何人进行交流 \n" + "B. 与特定的人或在特定的情况下才能进行交流 \n" + "\n" + "22．我更喜欢阅读\n" + "A. 内容独特、具有独创性的书籍\n" + "B. 条理清晰，准确表达作者想法的书籍\n" + "\n" + "23．我认为更不好的是\n" + "A. 过分亲切 \n" + "B. 不亲切 \n" + "\n" + "24．在日常生活中（这个问题可以选两个）\n" + "A. 我喜欢在迫在眉睫的情况下做事情\n" + "B. 我不喜欢做事时赶时间\n" + "C. 为了不赶时间，我一般事先制定计划 \n" + "\n" + "25．与我初次见面的人\n" + "A. 很快就了解我的兴趣所在 \n" + "B. 有了较深的交往以后才知道我的兴趣所在 \n" + "\n" + "26．处理日常事务时，我通常\n" + "A. 喜欢按照惯例处理\n" + "B. 喜欢想出独特的方法去处理 \n" + "\n" + "27．我更关注的是\n" + "A. 尊重别人的感情 \n" + "B. 保护别人的权利 \n" + "\n" + "28．需要处理特殊的事情时，我通常\n" + "A. 事先小心制定计划 \n" + "B. 在处理事情的过程中根据情况找出相应的对策 \n" + "\n" + "29．我一般\n" + "A. 能够自由地表达自己的情感和感受\n" + "B. 不愿流露自己的情感和感受，而是将它们埋在心里\n" + "\n" + "30．我更偏好\n" + "A. 独具一格的生活方式 \n" + "B. 普普通通的生活方式\n" + "\n" + "31．我认为更贴近自己的是\n" + "A. 温和的\n" + "B. 强健的\n" + "\n" + "32．在做某件事情时若事先被告知实施计划\n" + "A. 我感到很高兴，因为这样我就可以事先做好准备\n" + "B. 我感到有些懊恼，因为我讨厌被束缚\n" + "\n" + "33．做事情时，我一般\n" + "A. 比别人更积极 \n" + "B. 不如别人积极 \n" + "\n" + "34．我认为更贴近自己的词是\n" + "A. 愿景（Vision）\n" + "B. 常识\n" + "\n" + "35．我认为更贴近自己的词是\n" + "A. 思考\n" + "B. 情感\n" + "\n" + "36．我通常\n" + "A. 喜欢接近截止日期处理事情 \n" + "B. 接近截止日期处理事情感到焦虑 \n" + "\n" + "37．在聚会或派对上\n" + "A. 我有时感到很无聊 \n" + "B. 一般来说都是很愉快的 \n" + "\n" + "38．我认为更重要的是\n" + "A. 在现有环境中找出可能性 \n" + "B. 适应现有的环境 \n" + "\n" + "39．我认为更贴近自己的是\n" + "A. 说服人\n" + "B. 感动人\n" + "\n" + "40．我认为处理每天的日常事务\n" + "A. 没什么不适\n" + "B. 有必要，但每天重复同一件事情会感到不舒服\n" + "\n" + "41．我通常\n" + "A. 在流行的前头\n" + "B. 对流行不感兴趣 \n" + "\n" + "42．一般来说我会\n" + "A. 支持现有体制 \n" + "B. 分析现有体制的问题，并提示改善方案\n" + "\n" + "43．我认为更贴近自己的是\n" + "A. 分析\n" + "B. 同情\n" + "\n" + "44．对应处理的琐事或应购买的东西，\n" + "A. 我经常忘记，事后才想起来 \n" + "B. 为了不忘记，我将这些写在记事本上 \n" + "C. 我会及时处理这些事情 \n" + "\n" + "45．别人一般\n" + "A. 较容易与我交上朋友\n" + "B. 不太容易与我交上朋友 \n" + "\n" + "46．我认为更贴近自己的词是\n" + "A. 事实\n" + "B. 主意（Idea）\n" + "\n" + "47．我认为更贴近自己的是\n" + "A. 正义\n" + "B. 慈爱\n" + "\n" + "48．对我来说更难适应的是\n" + "A. 重复日常事务\n" + "B. 不断变化\n" + "\n" + "49．谈话中若出现窘迫局面，我会\n" + "A. 改换话题 \n" + "B. 开玩笑来摆脱窘迫局面 \n" + "C. 几天以后才想起当时应该怎样说 \n" + "\n" + "50．我认为更贴近自己的是\n" + "A. 描述事实\n" + "B. 抽象思维\n" + "\n" + "51．我认为更贴近自己的是\n" + "A. 内心温暖\n" + "B. 思维犀利\n" + "\n" + "52．处理一周内必须完成的大事时，我会\n" + "A. 首先制定计划，决定事情的先后顺序\n" + "B. 马上着手处理\n" + "\n" + "53．与我亲近的人\n" + "A. 一般了解我的感受 \n" + "B. 若我不说出来还是不太了解我的感受\n" + "\n" + "54．我认为更贴近自己的是\n" + "A. 推论\n" + "B. 确证\n" + "\n" + "55．我认为更贴近自己的是\n" + "A. 有益于人\n" + "B. 施舍于人\n" + "\n" + "56．处理事情时，我一般\n" + "A. 提前开始提早完成 \n" + "B. 快到截止日期急忙完成 \n" + "\n" + "57．聚会时我喜欢\n" + "A. 推动聚会顺利进行 \n" + "B. 让人们自得其乐 \n" + "\n" + "58．我认为更贴近自己的是\n" + "A. 文字本意\n" + "B. 比喻\n" + "\n" + "59．我认为更贴近自己的是\n" + "A. 判决\n" + "B. 奉献\n" + "\n" + "60．如果在某个休息日早上有人问我怎么过一天，我会\n" + "A. 马上说出做什么 \n" + "B. 罗列出很多要做的事情 \n" + "C. 说到时候才能知道该做什么 \n" + "\n" + "61．我认为更贴近自己的是\n" + "A. 充满活力\n" + "B. 安静\n" + "\n" + "62．我认为更贴近自己的是\n" + "A. 想象\n" + "B. 事实\n" + "\n" + "63．我认为更贴近自己的是\n" + "A. 坚定的意志\n" + "B. 温柔的心\n" + "\n" + "64．对我来说日常事务\n" + "A. 容易、舒心\n" + "B. 无聊 \n" + "\n" + "65．以下各题中最贴近我的是\n" + "A. 沉默寡语\n" + "B. 健谈\n" + "\n" + "66．以下各题中最贴近我的是\n" + "A. 制作\n" + "B. 创作\n" + "\n" + "67．以下各题中最贴近我的是\n" + "A. 调解\n" + "B. 判定\n" + "\n" + "68．以下各题中最贴近我的是\n" + "A. 计划\n" + "B. 无计划\n" + "\n" + "69．以下各题中最贴近我的是\n" + "A. 沉着\n" + "B. 活泼\n" + "\n" + "70．以下各题中最贴近我的是\n" + "A. 分辨能力\n" + "B. 魅力\n" + "\n" + "71．以下各题中最贴近我的是\n" + "A. 温柔\n" + "B. 强壮\n" + "\n" + "72．以下各题中最贴近我的是\n" + "A. 系统化\n" + "B. 自由化\n" + "\n" + "73．以下各题中最贴近我的是\n" + "A. 说\n" + "B. 写\n" + "\n" + "74．以下各题中最贴近我的是\n" + "A. 制作\n" + "B. 设计\n" + "\n" + "75．以下各题中最贴近我的是\n" + "A. 原谅\n" + "B. 容忍\n" + "\n" + "76．以下各题中最贴近我的是\n" + "A. 按照计划\n" + "B. 根据情况\n" + "\n" + "77．以下各题中最贴近我的是\n" + "A. 社交\n" + "B. 超然自得\n" + "\n" + "78．以下各题中最贴近我的是\n" + "A. 具体\n" + "B. 抽象\n" + "\n" + "79．以下各题中最贴近我的是\n" + "A. 人\n" + "B. 事\n" + "\n" + "80．以下各题中最贴近我的是\n" + "A. 冲动\n" + "B. 决断\n" + "\n" + "81．以下各题中最贴近我的是\n" + "A. 聚会\n" + "B. 读书\n" + "\n" + "82．以下各题中最贴近我的是\n" + "A. 制造\n" + "B. 发明\n" + "\n" + "83．以下各题中最贴近我的是\n" + "A. 接纳\n" + "B. 判断\n" + "\n" + "84．以下各题中最贴近我的是\n" + "A. 按时\n" + "B. 随时\n" + "\n" + "85．以下各题中最贴近我的是\n" + "A. 基石\n" + "B. 榜样\n" + "\n" + "86．以下各题中最贴近我的是\n" + "A. 小心谨慎\n" + "B. 不拘小节\n" + "\n" + "87．以下各题中最贴近我的是\n" + "A. 不断变化\n" + "B. 永久\n" + "\n" + "88．以下各题中最贴近我的是\n" + "A. 理论\n" + "B. 经验\n" + "\n" + "89．以下各题中最贴近我的是\n" + "A. 同意\n" + "B. 讨论\n" + "\n" + "90．以下各题中最贴近我的是\n" + "A. 规律\n" + "B. 随意\n" + "\n" + "91．以下各题中最贴近我的是\n" + "A. 图画\n" + "B. 文字\n" + "\n" + "92．以下各题中最贴近我的是\n" + "A. 迅速\n" + "B. 慎重\n" + "\n" + "93．以下各题中最贴近我的是\n" + "A. 接受\n" + "B. 改变\n" + "\n" + "94．以下各题中最贴近我的是\n" + "A. 已知\n" + "B. 未知\n";

    public static void main(String[] args) {

                mbti();
//        oq45();
//        scl90();
    }

    private static void mbti() {
        QuestionDto questionDto = new QuestionDto();

        List<QuestionDto.QuestionsBean> questions = new ArrayList<>();

        String[] split = question.split("\n\n");
        int count = 0;
        for (String str : split) {
            String[] split1 = str.split("\n");
            QuestionDto.QuestionsBean questionsBean = new QuestionDto.QuestionsBean();
            List<QuestionDto.QuestionsBean.AnswerBean> answer = new ArrayList<>();
            String[] split2 = split1[0].split("．");
            questionsBean.setNum(Integer.parseInt(split2[0]));
            questionsBean.setTopic(split2[1]);
            questionsBean.setAnswer(answer);
            String[] split3 = split1[1].trim().split(". ");
            QuestionDto.QuestionsBean.AnswerBean answerBean = new QuestionDto.QuestionsBean.AnswerBean();

            answerBean.setOptions(split3[0]);
            answerBean.setDescription(split3[1]);
            answer.add(answerBean);
            String[] split4 = split1[2].trim().split(". ");
            answerBean = new QuestionDto.QuestionsBean.AnswerBean();

            answerBean.setOptions(split4[0]);
            answerBean.setDescription(split4[1]);
            answer.add(answerBean);

            if (split1.length > 3) {
                String[] split5 = split1[3].split(". ");
                answerBean = new QuestionDto.QuestionsBean.AnswerBean();

                answerBean.setOptions(split5[0]);
                answerBean.setDescription(split5[1]);
                answer.add(answerBean);
            }
            questions.add(questionsBean);
            count++;

        }
        questionDto.setQuestions(questions);
        questionDto.setTotal(count);
        System.out.println(JSON.toJSON(questionDto));
    }

    private final static String question1 = "1\t我和其他人相处得好。\t从未4   很少有3   有时有2  较多时候1  总是这样0\t\t\t\n" + "2\t我容易疲劳。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "3\t我对什么事都不感兴趣。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "4\t我感到来自工作/学习的压力。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "5\t我为不少事情责备自己。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "6\t我感到烦躁。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "7\t我对我的婚姻/重要关系的人感到不满。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "8\t我有轻生的念头。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "9\t我感到无能为力。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "10\t我感到恐惧。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "11\t每当狂饮后翌日早上，我需要再喝酒才能继续办事。（不喝酒请填『从未』）\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "12\t我对自己的工作/学习感到满意。\t从未4   很少有3   有时有2  较多时候1  总是这样0\t\t\t\n" + "13\t我是个快乐的人。\t从未4   很少有3   有时有2  较多时候1  总是这样0\t\t\t\n" + "14\t我承担了过量的工作/学习任务。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "15\t我觉得自己不值一文。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "16\t我关注家中出现的问题。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "17\t我不满意我的性生活。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "18\t我感到孤单。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "19\t我经常与人发生争执。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "20\t我感到有人爱我和需要我。\t从未4   很少有3   有时有2  较多时候1  总是这样0\t\t\t\n" + "21\t我享受闲暇。\t从未4   很少有3   有时有2  较多时候1  总是这样0\t\t\t\n" + "22\t我难于集中精神。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "23\t我对未来感到绝望。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "24\t我喜欢自己。\t从未4   很少有3   有时有2  较多时候1  总是这样0\t\t\t\n" + "25\t我无法除去脑海中一些困扰自己的思想。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "26\t我对批评我喝酒（或使用药物）的人感到厌烦。（如不适用请填『从未』）\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "27\t我胃部不舒服。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "28\t我的工作/学习没有像往常那样好。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "29\t我的心跳得太快或太沉重。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "30\t我无法和朋友/同伴很好相处。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "31\t我对自己的生活感到满意。\t从未4   很少有3   有时有2  较多时候1  总是这样0\t\t\t\n" + "32\t因喝酒或使用药物，我在工作/学校中遇到困难（如不适用请填『从未』）\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "33\t我有一种不祥的预感。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "34\t我的肌肉酸痛。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "35\t我对户外空间、驾车或在公交车、地铁等感到恐惧。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "36\t我感到紧张焦虑。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "37\t我觉得我与爱侣的关系美满。\t从未4   很少有3   有时有2  较多时候1  总是这样0\t\t\t\n" + "38\t我觉得自己在工作/学校中的表现不佳。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "39\t我在工作/学校中与人有太多的分歧。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "40\t我觉得自己的思想有点问题。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "41\t我难以入睡，或睡不沉。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "42\t我感到沮丧。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "43\t我满意自己与他人的关系。\t从未4   很少有3   有时有2  较多时候1  总是这样0\t\t\t\n" + "44\t对工作/学校的怨愤，足以使我做出一些可能感到后悔的事情。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n" + "45\t我有头痛的现象。\t从未0   很少有1   有时有2  较多时候3  总是这样4\t\t\t\n";

    private static void oq45() {
        QuestionDto questionDto = new QuestionDto();

        List<QuestionDto.QuestionsBean> questions = new ArrayList<>();

        String[] split = question1.split("\n");
        int count = 0;

        for (String str : split) {
            String[] split1 = str.split("\t");
            QuestionDto.QuestionsBean questionsBean = new QuestionDto.QuestionsBean();
            List<QuestionDto.QuestionsBean.AnswerBean> answer = new ArrayList<>();
            questionsBean.setNum(Integer.parseInt(split1[0]));
            questionsBean.setTopic(split1[1]);
            questionsBean.setAnswer(answer);
            String[] split2 = split1[2].split(" ");
            for (int i = 0; i < split2.length; i++) {
                if (!"".equals(split2[i])) {
                    QuestionDto.QuestionsBean.AnswerBean answerBean = new QuestionDto.QuestionsBean.AnswerBean();
                    answerBean.setOptions(String.valueOf(i));
                    switch (i) {
                        case 0:
                            answerBean.setOptions("A");
                            break;
                        case 3:
                            answerBean.setOptions("B");
                            break;
                        case 6:
                            answerBean.setOptions("C");
                            break;
                        case 8:
                            answerBean.setOptions("D");
                            break;
                        case 10:
                            answerBean.setOptions("E");
                            break;
                    }

                    answerBean.setDescription(split2[i].substring(0, split2[i].length() - 1));
                    answer.add(answerBean);
                }
            }
            questions.add(questionsBean);
            count++;

        }
        questionDto.setQuestions(questions);
        questionDto.setTotal(count);
        System.out.println(JSON.toJSON(questionDto));

    }

    private final static String question2 = "1、头痛\n" + "2、神经过敏，心中不踏实\n" + "3、头脑中有不必要的想法或字句盘旋\n" + "4、头昏或昏倒\n" + "5、对异性的兴趣减退\n" + "6、对旁人责备求全\n" + "7、感到别人能控制您的思想\n" + "8、责怪别人制造麻烦\n" + "9、忘记性大\n" + "10、担心自己的衣饰整齐及仪态的端正\n" + "11、容易烦恼和激动\n" + "12、胸痛\n" + "13、害怕空旷的场所或街道\n" + "14、感到自己的精力下降，活动减慢\n" + "15、想结束自己的生命\n" + "16、听到旁人听不到的声音\n" + "17、发抖\n" + "18、感到大多数人都不可信任\n" + "19、胃口不好\n" + "20、容易哭泣\n" + "21、同异性相处时感到害羞不自在\n" + "22、感到受骗、中了圈套或有人想抓住您\n" + "23、无缘无故地突然感到害怕\n" + "24、自己不能控制地大发脾气\n" + "25、怕单独出门\n" + "26、经常责怪自己\n" + "27、腰痛\n" + "28、感到难以完成任务\n" + "29、感到孤独\n" + "30、感到苦闷\n" + "31、过分担忧\n" + "32、对事物不感兴趣、\n" + "33、感到害怕\n" + "34、您的感情容易受到伤害\n" + "35、旁人能知道您的私下想法\n" + "36、感到别人不理解您、不同情您\n" + "37、感到人们对您不友好、不喜欢您\n" + "38、做事必须做得很慢以保证做得正确\n" + "39、心跳得很厉害\n" + "40、恶心或胃部不舒服\n" + "41、感到比不上他人\n" + "42、肌肉酸痛\n" + "43、感到有人在监视您、谈论您\n" + "44、难以入睡\n" + "45、做事必须反复检查\n" + "46、难以作出决定\n" + "47、怕乘电车、公共汽车、地铁或火车\n" + "48、呼吸有困难\n" + "49、一阵阵发冷或发热\n" + "50、因为感到害怕而避开某些东西、场合或活动\n" + "51、脑子变空了\n" + "52、身体发麻或刺痛\n" + "53、喉咙有梗塞感\n" + "54、感到没有前途没有希望\n" + "55、不能集中注意\n" + "56、感到身体的某一部分软弱无力\n" + "57、感到紧张或容易紧张\n" + "58、感到手或脚发重\n" + "59、想到死亡的事\n" + "60、吃得太多\n" + "61、当别人看着您或谈论您时感到不自在\n" + "62、有一些不属于您自己的想法\n" + "63、有想打人或伤害他人的冲动\n" + "64、醒得太早\n" + "65、必须反复洗手、点数目或触摸某些东西\n" + "66、睡得不稳不深\n" + "67、有想摔坏或破坏东西的冲动\n" + "68、有一些别人没有的想法或念头\n" + "69、感到对别人神经过敏\n" + "70、在商店或电影院等人多的地方感到不自在\n" + "71、感到任何事情都很困难\n" + "72、一阵阵恐惧或惊恐\n" + "73、感到在公共场合吃东西很不舒服\n" + "74、常与人争论\n" + "75、独自一人时神经很紧张\n" + "76、别人对您的成绩没有作出恰当的评价\n" + "77、即使和别人在一起也感到孤单\n" + "78、感到坐立不安、心神不定\n" + "79、感到自己没有什么价值\n" + "80、感到熟悉的东西变成陌生或不像是真的\n" + "81、大叫或摔东西\n" + "82、害怕会在公共场合昏倒\n" + "83、感到别人想占您的便宜\n" + "84、为一些有关“性”的想法而很苦恼\n" + "85、您认为应该因为自己的过错而受到惩罚\n" + "86、感到要赶快把事情做完\n" + "87、感到自己的身体有严重问题\n" + "88、从未感到和其他人很亲近\n" + "89、感到自己有罪\n" + "90、感到自己的脑子有毛病。";

    private static void scl90() {
        QuestionDto questionDto = new QuestionDto();

        List<QuestionDto.QuestionsBean> questions = new ArrayList<>();

        String[] split = question2.split("\n");
        int count = 0;

        for (String str : split) {
            String[] split1 = str.split("、");
            QuestionDto.QuestionsBean questionsBean = new QuestionDto.QuestionsBean();
            List<QuestionDto.QuestionsBean.AnswerBean> answer = new ArrayList<>();
            questionsBean.setNum(Integer.parseInt(split1[0]));
            questionsBean.setTopic(split1[1]);
            questionsBean.setAnswer(answer);
            for (int i = 0; i < 5; i++) {
                QuestionDto.QuestionsBean.AnswerBean answerBean = new QuestionDto.QuestionsBean.AnswerBean();
                switch (i) {
                    case 0:
                        answerBean.setOptions("A");
                        answerBean.setDescription("没有");
                        break;
                    case 1:
                        answerBean.setOptions("B");
                        answerBean.setDescription("较轻");
                        break;
                    case 2:
                        answerBean.setOptions("C");
                        answerBean.setDescription("中度");

                        break;
                    case 3:
                        answerBean.setOptions("D");
                        answerBean.setDescription("相当重");

                        break;
                    case 4:
                        answerBean.setOptions("E");
                        answerBean.setDescription("严重");

                        break;
                }
                answer.add(answerBean);
            }
            questions.add(questionsBean);
            count++;
        }

        questionDto.setQuestions(questions);
        questionDto.setTotal(count);
        System.out.println(JSON.toJSON(questionDto));
    }
}
