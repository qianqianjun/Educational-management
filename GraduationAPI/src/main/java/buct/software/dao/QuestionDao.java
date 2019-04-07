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
    //学生获取论题的部分内容 根据专业
    public List<QuestionStudentInquiry> getPartQuestionsByMajorid(int majorid);

    //老师，后台获取论题的全部内容 根据专业
    public List<Question> getQuestionsByMajorid(int majorid);

    //老师根据老师工号获取论题全部内容
    public List<Question> getQuestionByTno(int tno);

    //根据论题id获取单个论题
    public Question getQuestionByQustionId(int QuestionId);

    //老师添加论题
    public void addQuestion(Question question);

    //老师删除论题，不能删除已经确认学生选题的
    public void deleteQuestion(int questionId);

    //老师设置某个论题的学生，即确认某个论题对应的学生
    public void sureQuestionStudent(HashMap map);

    //根据学生号查找论题，便于确认学生是否已经有选题
    public Question getSingleQuestionBySno(int sno);
}
