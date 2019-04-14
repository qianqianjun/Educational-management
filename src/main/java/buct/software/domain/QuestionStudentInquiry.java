package buct.software.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author yuzhongrui
 * another class of question designed for Inquire
 */
@Data
@Alias(value = "QuestionStudentInquiry")
public class QuestionStudentInquiry {
    private String topic;
    private int difficulty;
    private int tno;
    private boolean ischosen;

    public QuestionStudentInquiry(){}
    public QuestionStudentInquiry(String topic,int difficulty,int tno,boolean ischosen){
        this.topic = topic;
        this.difficulty = difficulty;
        this.tno = tno;
        this.ischosen = ischosen;
    }
}
