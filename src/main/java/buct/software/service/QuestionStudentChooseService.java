package buct.software.service;

import buct.software.dao.QuestionStudentDao;
import buct.software.domain.Question;
import buct.software.domain.QuestionStudentChoose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import buct.software.service.QuestionService;

/**
 * @author yuzhongrui
 */

@Service
public class QuestionStudentChooseService {
    @Autowired
    QuestionStudentDao questionStudentDao;

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionStudentChooseService questionStudentChooseService;

    /**
     * 功能：往选题表中增加记录
     * 学生不能被论题绑定，论题也不能被任何学生选中
     * 成功返回true，失败返回false
     */
    public Boolean chooseQuestion(int questionid,int sno){
        Boolean isChoosen = false;
        Question question = questionService.getSingleQuestionByQuestionid(questionid);
        Question questionSno = questionService.getSingleQuestionBySno(sno);
        if(question==null)//论题不为空
            return isChoosen;
        if(question.isIschosen()==true || questionSno!=null)//论题没有被选择，学生也没有绑定任何论题
            return isChoosen;

        QuestionStudentChoose questionStudentChoose = questionStudentChooseService.getChoiceByQidSno(questionid,sno);
        if (questionStudentChoose!=null)//如果该论题和学生的连接 已经投递，则显示已经插入成功
            return true;

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("questionid",questionid);
        map.put("sno",sno);
        questionStudentDao.chooseQuestion(map);
        if(questionStudentDao.getChoiceByQidSno(map)!=null)
            isChoosen = true;

        return isChoosen;
    }



    //根据学生号选择该学生所有的选题信息
    public List<QuestionStudentChoose> getChoiceBySno(int sno){
        return questionStudentDao.getChoiceBySno(sno);
    }

    //根据老师工号选择选题信息
    public List<QuestionStudentChoose> getChoiceByTno(int tno){
        return questionStudentDao.getChoiceByTno(tno);
    }


    //根据论题号和学生号获得论题选择信息
    public QuestionStudentChoose getChoiceByQidSno(int questionid,int sno){
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("questionid",questionid);
        map.put("sno",sno);
        return questionStudentDao.getChoiceByQidSno(map);
    }

    //根据题目找到所有选该题的学生
    public List<QuestionStudentChoose> getChoiceByQid(int questionid){
        return questionStudentDao.getChoiceByQid(questionid);
    }

}
