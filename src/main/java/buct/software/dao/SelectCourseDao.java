package buct.software.dao;
import buct.software.domain.ScheduleMajor;
import buct.software.domain.SelectCourse;
import buct.software.views.SelectCourseView;
import buct.software.views.StudentGradeIndexView;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author  高谦
 * 查询选课相关信息的数据库访问层。
 */
@Repository
public interface SelectCourseDao {
    /**
     * 获取所有的课程
     * @return
     */
    List<SelectCourseView> getAllAvaiableCourse(ScheduleMajor parm);

    /**
     * 根据查询条件返回结果
     * @param view 条件集合
     * @return  返回查询到的结果。
     */
    //SelectCourseView view
    List<SelectCourseView> getAllAvaiableCourseWithCondition(SelectCourseView view);

    /**
     * 根据学号和当前学期，获取这个学生已选的全部课程
     * @param  selectCourse  查询条件，里面包含学号和学期id
     * @return  返回已选课程列表
     */
    List<SelectCourse> getAllCourseHaveBeenSelected(SelectCourse selectCourse);

    /**
     * 增加选课信息，将一个没有选择的课程添加到学生课表中。
     * @param selectCourse
     * @return  返回一个整数 ，表示sql 语句影响的数据库行数
     */
    Integer addNewCourseToTable(SelectCourse selectCourse);


    /**
     *  用于删除一个选课表中的数据
     *  也就是取消选课
     * @param selectCourse   要取消的课程的信息。
     * @return
     */
    Integer deleteCourseFromTable(SelectCourse selectCourse);

    /**
     * 获得学生选课的课表
     * @param selectCourse
     * @return
     */
    List<SelectCourseView> getCourseTable(SelectCourse selectCourse);

    /**
     * 获取成绩列表
     * @return 成绩列表
     */
    List<StudentGradeIndexView> getGrade(SelectCourse selectCourse);
}
