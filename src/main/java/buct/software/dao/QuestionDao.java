package buct.software.dao;

import buct.software.domain.Question;
import buct.software.domain.QuestionStudentInquiry;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author yuzhongrui
 * define database-access-operation
 */
@Repository
public interface QuestionDao {
    /**
     * 功能：根据专业 获取论题的部分内容
     * 服务对象：学生
     * @param majorid 专业id
     * @return  论题部分信息列表
     * String topic;int difficulty;String tname;boolean ischosen;
     */
    public List<QuestionStudentInquiry> getPartQuestionsByMajorid(int majorid);
    /**
     * 功能：根据论题id 获取论题的全部信息
     * 服务对象：学生
     * @param QuestionId 论题id
     * @return 一个论题信息
     */
    public Question getQuestionByQustionId(int QuestionId);
    /**
     * 功能：添加论题
     * 服务对象：老师
     * @param question Question类
     */
    public void addQuestion(Question question);
    /**
     * 功能：根据论题号删除论题，不能删除已经确认学生选题的
     * 服务对象：老师
     * @param questionId 论题id
     */
    public void deleteQuestion(int questionId);
    /**
     * 功能：确定论题和学生之间的连接
     * 服务对象：老师
     * @param map (questionid,sno)
     */
    public void sureQuestionStudent(HashMap map);
    /**
     * 功能：根据老师工号获取论题全部内容
     * @param tno 教师工号
     * @return 论题列表
     */
    public List<Question> getQuestionByTno(int tno);
    /**
     * 功能：根据专业 获取论题的全部内容
     * 服务对象：后台
     * @param majorid 专业id
     * @return 论题信息列表
     */
    public List<Question> getQuestionsByMajorid(int majorid);

    public List<Question> getAllQuestions();

    //删除学生和选题之间的连接
    public void deleteStudentQuestion(int questionid);


    //根据学生号查找论题，便于确认学生是否已经有选题
    public Question getSingleQuestionBySno(int sno);
}
