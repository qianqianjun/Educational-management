package buct.software.domain;


import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author yuzhongrui
 * mapping database-table question
 */

@Data
@Alias(value = "Question")
public class Question {
    private int questionid;
    private String topic;
    private String content;
    private int difficulty;
    private int tno;
    private int majorid;
    private int sno;
    private boolean ischosen;

    public Question(){}
    public Question(String topic,String content, int difficulty,
                    int tno,int majorid) {
        this.topic = topic;
        this.content = content;
        this.difficulty = difficulty;
        this.tno = tno;
        this.majorid = majorid;
    }
    public Question(int questionid,String topic,String content, int difficulty,
                    int tno,int majorid,int sno,boolean ischosen) {
        this.questionid = questionid;
        this.topic = topic;
        this.content = content;
        this.difficulty = difficulty;
        this.tno = tno;
        this.majorid = majorid;
        this.sno = sno;
        this.ischosen = ischosen;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public void setIschosen(boolean ischosen) {
        this.ischosen = ischosen;
    }

    public int getQuestionid() {
        return questionid;
    }

    public String getTopic() {
        return topic;
    }

    public boolean isIschosen() {
        return ischosen;
    }
}


