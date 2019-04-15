package buct.software.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author yuzhongrui
 */
@Data
@Alias("QuestionScore")
public class QuestionScore {
    private int sno;
    private int questionid;
    private int earlyperformance;
    private int midexam;
    private int thesisanswer;
    private int paper;
    private int extracredit;

    public QuestionScore(){}

    public QuestionScore(int sno,int questionid,int earlyperformance,
                         int midexam,int thesisanswer,int paper,int extracredit){
        this.sno = sno;
        this.questionid = questionid;
        this.earlyperformance = earlyperformance;
        this.midexam = midexam;
        this.thesisanswer = thesisanswer;
        this.paper = paper;
        this.extracredit = extracredit;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }

    public void setEarlyperformance(int earlyperformance) {
        this.earlyperformance = earlyperformance;
    }

    public void setMidexam(int midexam) {
        this.midexam = midexam;
    }

    public void setThesisanswer(int thesisanswer) {
        this.thesisanswer = thesisanswer;
    }

    public void setPaper(int paper) {
        this.paper = paper;
    }

    public void setExtracredit(int extracredit) {
        this.extracredit = extracredit;
    }
}
