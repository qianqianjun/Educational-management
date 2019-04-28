package buct.software.dao;
import buct.software.domain.Scheduling;
import buct.software.domain.SelectCourse;
import buct.software.views.MobileSchedulingView;
import buct.software.views.SchedulingView;
import buct.software.views.SchedulingCourseView;
import org.springframework.stereotype.Repository;

import java.util.List;



import buct.software.views.SchedulingView;
import org.springframework.stereotype.Repository;

/**
 * @author 高谦
 * 排课表 scheduling 数据库操作类
 * @implement 实现者名字
 */
@Repository
public interface SchedulingDao {
    public SchedulingView getCourse(Integer semesterId,Integer cno);
    public Boolean deleteCourse(Integer semesterId,Integer cno);
    public Boolean deleteCourseMajor(Integer semesterId,Integer cno,Integer majorId,Integer grade);
    public Boolean addCourse(Integer semesterId,Integer cno,Integer tno,
                             String status,Integer capacity,String address,Integer grade,String time);
    public Boolean addCourseMajor(Integer semesterId,Integer cno,Integer majorId,Integer grade);
    public Integer getCourseMajorCount(Integer semesterId,Integer cno);
    public Integer checkCourseMajor(Integer semesterId,String cno,Integer majorId,Integer grade);
    public List<MobileSchedulingView> getCoursesByTnoAndTnameAndAddress(Integer tno, String tname,String address);
    public List<SchedulingCourseView> getAllCourses(String year, String semester, String majorName, Integer grade);

    //几个用于检测冲突的查询方法

    /**
     * 获取教师(学生)在这个学期已经安排好的任务的时间字符串列表
     * @return  返回老师的任务时间列表
     */
    public List<String> getTeacherTaskTime(Scheduling scheduling);
    public List<String> getStudentTaskTime(SelectCourse selectCourse);

    /**
     * 查询某一个教室的所有使用时间。
     * @return
     */

    public List<String> getRoomTaskTime(Scheduling scheduling);



}
