package buct.software.service;

import buct.software.dao.QuestionDao;
import buct.software.domain.Question;
import buct.software.domain.QuestionStudentInquiry;
import buct.software.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author yuzhongrui
 * database-table question operation
 */
@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    //若查询单个为空 返回null
    //若查询列表为空 返回[]


    /**
     * 功能：根据专业 获取论题的部分内容
     * 服务对象：学生
     * @param majorid 专业id
     * @return  论题部分信息列表
     * String topic;int difficulty;String tname;boolean ischosen;
     */
    public List<QuestionStudentInquiry> getPartQuestionByMajorid(int majorid){
        return questionDao.getPartQuestionsByMajorid(majorid);
    }
    /**
     * 功能：根据论题号查询单个题目的全部信息(为了删除和确认学生等逻辑)
     * 服务对象：学生
     * @param questionid 论题id
     * @return  一个论题的全部信息
     */
    public Question getSingleQuestionByQuestionid(int questionid){
        return questionDao.getQuestionByQustionId(questionid);
    }
    /**
     * 功能：添加论题
     * 服务对象：老师
     * @param question Question类
     * @return 添加成功则返回true，否则返回false
     */
    public Boolean addQuestion(Question question){
        /*
        用当前的年的最后两位和日和具体时分秒表示论题id
        */
        String[] strTimes = new SimpleDateFormat("yy-MM-dd-hh-mm-ss")
                .format(new Date()).split("-");
        String strTime = strTimes[0]+strTimes[2]+strTimes[3]+strTimes[4]+strTimes[5];
        int intTime = Integer.valueOf(strTime);
        question.setQuestionid(intTime);
        //是否选择为假
        question.setIschosen(false);
        //学号设为-1表示没有人选
        question.setSno(-1);
        questionDao.addQuestion(question);
        return questionDao.getQuestionByQustionId(intTime)!=null;
    }
    /**
     * 功能：根据论题号删除论题，不能删除已经确认学生选题的,必须有该论题且未被选中才能删除
     * 服务对象：老师
     * @param questionid 论题id
     * @return 已删除返回true，未删除返回false
     */
    public Boolean deleteQuestionByQuestionid(int questionid){
        boolean canDelete=false;
        Question question = questionDao.getQuestionByQustionId(questionid);
        if(question==null)
            return canDelete;
        if (question.isIschosen()==false) {
            questionDao.deleteQuestion(questionid);
            canDelete=true;
        }
        return canDelete;
    }
    /**
     * 功能：确认学生和选题之间的连接，必须有该论题且该论题未被选中;并且学生也没有被任何其他论题选中
     * 服务对象：老师
     * @param questionId 论题id
     * @param sno  学号
     * @return 选择成功返回true，失败返回false
     */
    public Boolean sureQuestionStudent(int questionId,int sno){
        Boolean canSure = false;
        HashMap<String,Object> map = new HashMap<String, Object>();
        Question question = questionDao.getQuestionByQustionId(questionId);
        if(question==null || questionDao.getSingleQuestionBySno(sno)!=null)
            return canSure;
        if(question.isIschosen()==false){
            map.put("id",questionId);
            map.put("sno",sno);
            questionDao.sureQuestionStudent(map);
            canSure = true;
        }
        return canSure;
    }
    /**
     * 功能：根据老师工号查询论题表的全部信息
     * 服务对象：老师
     * @param tno 老师工号
     * @return 论题列表
     */
    public List<Question> getQuestionByTno(int tno){
        return questionDao.getQuestionByTno(tno);
    }
    /**
     * 功能：根据专业 获取论题的全部内容
     * 服务对象：后台
     * @param majorid 专业id
     * @return 论题信息列表
     */
    public List<Question> getQuestionByMajorid(int majorid){
        return questionDao.getQuestionsByMajorid(majorid);
    }

    public List<Question> getAllQuestions(){
        return questionDao.getAllQuestions();
    }


    /**
     * 功能：根据学生号查找论题
     * 为了便于确认选题时 学生和论题之间一一对应的关系
     */
    public Question getSingleQuestionBySno(int sno){
        return questionDao.getSingleQuestionBySno(sno);
    }





    //以下函数仅为测试功能的函数
    private ResponseMessage setMessage(List<Question> questions){
        ResponseMessage message = ResponseMessage.getMessage(
                questions!=null && !questions.isEmpty(),
                ResponseMessage.SUCCESS, "Inquire Success !",
                ResponseMessage.EmptyDate,"No Information Found !"
        );
        message.setData(questions);
        return message;
    }


    private ResponseMessage setMessagePart(List<QuestionStudentInquiry> questions){
        ResponseMessage message = ResponseMessage.getMessage(
                questions!=null && !questions.isEmpty(),
                ResponseMessage.SUCCESS, "Inquire Success !",
                ResponseMessage.EmptyDate,"No Information Found !"
        );
        message.setData(questions);
        return message;
    }


    private ResponseMessage setMessageSingle(Question question){
        ResponseMessage message = ResponseMessage.getMessage(
                question!=null,
                ResponseMessage.SUCCESS, "Inquire Success !",
                ResponseMessage.EmptyDate,"No Information Found !"
        );
        message.setData(question);
        return message;
    }


    public ResponseMessage messageGetQuestionByMajorid(int majorid){
        List<Question> questions = this.getQuestionByMajorid(majorid);
        return this.setMessage(questions);
    }


    public ResponseMessage messageGetPartQuestionByMajorid(int majorid){
        List<QuestionStudentInquiry> questions = this.getPartQuestionByMajorid(majorid);
        return this.setMessagePart(questions);
    }


    public ResponseMessage messageGetQuestionByTno(int tno){
        List<Question> questions = this.getQuestionByTno(tno);
        return setMessage(questions);
    }


    public ResponseMessage messageGetQuestionByQuestionid(int questionid){
        Question question = this.getSingleQuestionByQuestionid(questionid);
        return setMessageSingle(question);
    }


    public ResponseMessage messageAddQuestion(Question question){
        Boolean  isAdded = this.addQuestion(question);
        ResponseMessage message = ResponseMessage.getMessage(isAdded,
                ResponseMessage.SUCCESS, "Add Success！",
                ResponseMessage.EmptyDate,"Add Fail !"
        );
        return message;
    }


    public ResponseMessage messageDeleteQuestion(int questionid){
        Boolean isDeleted = this.deleteQuestionByQuestionid(questionid);
        ResponseMessage message = ResponseMessage.getMessage(isDeleted,
                ResponseMessage.SUCCESS, "Delete Success!",
                ResponseMessage.EmptyDate,"Delete Fail!"
        );
        return message;
    }

    public ResponseMessage messageSureQuestionStudent(int questionid,int sno){
        Boolean isSured = this.sureQuestionStudent(questionid,sno);
        ResponseMessage message = ResponseMessage.getMessage(isSured,
                ResponseMessage.SUCCESS, "Sure Success!",
                ResponseMessage.EmptyDate,"Sure Fail!"
        );
        return message;
    }

    public ResponseMessage messageGetSingleQuestionBySno(int sno){
        Question question = this.getSingleQuestionBySno(sno);
        return setMessageSingle(question);
    }

}
