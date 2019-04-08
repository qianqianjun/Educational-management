package buct.software.dao;
import buct.software.domain.ScheduleMajor;
import buct.software.domain.SelectCourse;
import buct.software.views.SelectCourseView;
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
}
