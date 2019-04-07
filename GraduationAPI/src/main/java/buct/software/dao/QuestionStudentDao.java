package buct.software.dao;

import buct.software.domain.QuestionStudentChoose;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author yuzhongrui
 */
@Repository
public interface QuestionStudentDao {
    //根据学生学号和论题号查询，即判断是否插入成功
    public QuestionStudentChoose getChoiceByQidSno(HashMap map);

    //学生选择某个题目
    public void chooseQuestion(HashMap map);//int questionid,int sno




















//    //获得某个学生的所有选题信息
//    public List<QuestionStudentChoose> getChoiceBySno(int sno);

//    //学生删除自己的某个选题
//    public void deleteChoice(int questionid,int sno);
//
//    //老师查看自己选题的学生 由于需要其他表，暂时做只能获取学号的
////    public List<QuestionStudentChoose> TeacherStudent(int )
//
//
//    //后台爱获取所有选题信息
//    public List<QuestionStudentChoose> getAllChoice();

}
