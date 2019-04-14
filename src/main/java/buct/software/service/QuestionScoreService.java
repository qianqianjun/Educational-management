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
    public List<QuestionScore> getQuestionScoreBySno(int sno){
        return questionScoreDao.getQuestionScoreBySno(sno);
    }

    //根据老师工号获取老师的题目所属的成绩信息
    public List<QuestionScore> getQuestionScoreByTno(int tno){
        return questionScoreDao.getQuestionScoreByTno(tno);
    }



}
