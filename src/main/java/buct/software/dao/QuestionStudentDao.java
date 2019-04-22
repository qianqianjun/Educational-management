package buct.software.dao;

import buct.software.domain.Question;
import buct.software.domain.QuestionStudentChoose;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author yuzhongrui
 */
@Repository
public interface QuestionStudentDao {


    //学生选择某个题目
    public void chooseQuestion(HashMap map);//int questionid,int sno
    //学生根据学号查询所有自己选过的题目
    public List<QuestionStudentChoose> getChoiceBySno(int sno);
    //老师根据工号查询所有选自己题的学生
    public List<QuestionStudentChoose> getChoiceByTno(int tno);
    //根据学生学号和论题号查询，为了判断是否插入选题表成功
    public QuestionStudentChoose getChoiceByQidSno(HashMap map);//int questionid,int sno
    //根据题目找到所有选该题的学生
    public List<QuestionStudentChoose> getChoiceByQid(int questionid);




    //学生删除某个题目
//    public void deleteQuestionStudent(HashMap map);//int questionid,int sno
    //学生根据学号查询所有自己选过的题目
//    public List<Question> getQuestionOfStudent(int sno);
//    //老师查看自己选题的学生 由于需要其他表，暂时做只能获取学号的
////    public List<QuestionStudentChoose> TeacherStudent(int )
//    //后台爱获取所有选题信息
//    public List<QuestionStudentChoose> getAllChoice();

}
