package cn.com.eap.web.dto;

import java.util.List;

/**
 * Created by xin.l on 2018/10/6.
 *
 * @author xin.l
 */
public class QuestionDto {


    /**
     * total : 98
     * questions : [{"num":1,"topic":"我通常","answer":[{"options":"A","description":"善于跟别人交往"},{"options":"B","description":"沉默寡语"}]},{"num":2,"topic":"我通常","answer":[{"options":"A","description":"善于跟别人交往"},{"options":"B","description":"沉默寡语"}]}]
     */

    private int total;
    private List<QuestionsBean> questions;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<QuestionsBean> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsBean> questions) {
        this.questions = questions;
    }

    public static class QuestionsBean {
        /**
         * num : 1
         * topic : 我通常
         * answer : [{"options":"A","description":"善于跟别人交往"},{"options":"B","description":"沉默寡语"}]
         */

        private int num;
        private String topic;
        private List<AnswerBean> answer;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public List<AnswerBean> getAnswer() {
            return answer;
        }

        public void setAnswer(List<AnswerBean> answer) {
            this.answer = answer;
        }

        public static class AnswerBean {
            /**
             * options : A
             * description : 善于跟别人交往
             */

            private String options;
            private String description;

            public String getOptions() {
                return options;
            }

            public void setOptions(String options) {
                this.options = options;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }
}
