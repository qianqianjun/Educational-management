package buct.software.service;

import buct.software.dao.QuestionScoreDao;
import buct.software.domain.QuestionScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuzhongrui
 */
@Service
public class QuestionScoreService {
    @Autowired
    QuestionScoreDao questionScoreDao;


    //修改某个学生的成绩
    //该学生必须有成绩
    //已修该返回true，否则false
    public boolean changeQuestionScore(QuestionScore questionScore){
        int sno = questionScore.getSno();
        if(questionScoreDao.getQuestionScoreBySno(sno)!=null){
            questionScoreDao.changeQuestionScore(questionScore);
            return true;
        }
        else
            return false;
    }



    //返回所有的成绩
    public List<QuestionScore> getAllQuestionScore(){
        return questionScoreDao.getAllQuestionScore();
    }

    //增加成绩
    public void addQuestionScore(QuestionScore questionScore){
//        questionScore = new QuestionScore();
//        questionScore.setSno(10);
//        questionScore.setQuestionid(1906033518);
//        questionScore.setEarlyperformance(100);
//        questionScore.setMidexam(80);
//        questionScore.setThesisanswer(90);
//        questionScore.setPaper(30);
//        questionScore.setExtracredit(100);
        questionScoreDao.addQuestionScore(questionScore);
    }

    //根据学号获取成绩信息

    public QuestionScore getQuestionScoreBySno(int sno){


        return questionScoreDao.getQuestionScoreBySno(sno);
    }

    //根据老师工号获取老师的题目所属的成绩信息
    public List<QuestionScore> getQuestionScoreByTno(int tno){
        return questionScoreDao.getQuestionScoreByTno(tno);
    }



}
