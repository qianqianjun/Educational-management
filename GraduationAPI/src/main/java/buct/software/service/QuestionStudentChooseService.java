package buct.software.service;

import buct.software.dao.QuestionStudentDao;
import buct.software.domain.Question;
import buct.software.domain.QuestionStudentChoose;
import buct.software.utils.ResponseMessage;
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

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("questionid",questionid);
        map.put("sno",sno);
        questionStudentDao.chooseQuestion(map);
        if(questionStudentDao.getChoiceByQidSno(map)!=null)
            isChoosen = true;
        System.out.println(isChoosen);
        return isChoosen;
    }

    /**
     * 功能：通过论题号和学生号查询某个选题表记录
     * 主要是辅助插入选题表之后的检测
     */
    public QuestionStudentChoose getChoiceByQidSno(int questionid,int sno){
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("questionid",questionid);
        map.put("sno",sno);
        return questionStudentDao.getChoiceByQidSno(map);
    }

    /**
     * 功能：删除某个选题表的记录；
     * 该记录不能是在论题表中绑定过的记录
     */
    public Boolean deleteQuestionStudent(int questionid,int sno){
        Boolean isDeleted = false;
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("questionid",questionid);
        map.put("sno",sno);
        if(questionStudentDao.getChoiceByQidSno(map)==null)//选题表中有这个记录
            return isDeleted;
        if(questionService.getSingleQuestionBySno(sno)!=null)//该学生没有被其他论题绑定
            return isDeleted;

        questionStudentDao.deleteQuestionStudent(map);
        if(questionStudentDao.getChoiceByQidSno(map)==null){
            isDeleted = true;
        }
        return isDeleted;
    }

    /**
     * 功能：获取某个学生所有的选题信息
     * 返回的是一个Question列表
     */
    public List<Question> getQuestionOfStudent(int sno){
        return questionStudentDao.getQuestionOfStudent(sno);
    }

    //以下是测试的message服务


    public ResponseMessage messageGetQuestionOfStudent(int sno){
        List<Question> questions = questionStudentDao.getQuestionOfStudent(sno);
        ResponseMessage message = ResponseMessage.getMessage(
                questions!=null&&!questions.isEmpty(),
                ResponseMessage.SUCCESS,"Inquire Success!",
                ResponseMessage.EmptyDate,"Inquire Fail!");
        message.setData(questions);
        return message;
    }



}
