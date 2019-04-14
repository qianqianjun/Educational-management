package buct.software.dao;

import buct.software.domain.SelectCourse;
import buct.software.views.SelectCourseView;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author  王艺琳
 * 查询成绩相关信息的数据库访问层。
 */
@Repository
public interface GradeManagementDao {

    /* 学生端 */
    /**
     * 根据查询条件返回结果
     * @param grade 条件集合
     * @return  返回查询到的结果。
     */
    //SelectCourse
    List<SelectCourse> getCourseGrade(SelectCourse grade);

    /* 教师端 */
    /**
     * 根据查询条件返回结果
     * @param studentGrade 条件集合
     * @return  返回查询到的结果。
     */
    //SelectCourse
    List<SelectCourse> getStudentGrade(SelectCourse studentGrade);

    /**
     * 修改某一个学生的成绩
     * @return  返回是否修改成功
     */
    Integer setStudentGradeById(SelectCourse studentGrade);


}
