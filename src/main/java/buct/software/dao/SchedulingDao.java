package buct.software.dao;
import buct.software.domain.Scheduling;
import buct.software.domain.SelectCourse;
import buct.software.views.*;
import org.apache.ibatis.annotations.Param;
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
    public SchedulingView getCourse(@Param("semesterId") Integer semesterId,@Param("cno") Integer cno);
    public Boolean deleteCourse(@Param("semesterId") Integer semesterId,@Param("cno") Integer cno);
    public Boolean deleteCourseMajor(@Param("semesterId") Integer semesterId,@Param("cno") Integer cno,@Param("majorId") Integer majorId,@Param("grade") Integer grade);
    public Boolean addCourse(@Param("semesterId") Integer semesterId,@Param("cno") Integer cno,@Param("tno") Integer tno,
                             @Param("status") String status,@Param("capacity") Integer capacity,@Param("address") String address,@Param("grade") Integer grade,
                             @Param("time") String time);
    public Boolean addCourseMajor(@Param("semesterId") Integer semesterId,@Param("cno") Integer cno,@Param("majorId") Integer majorId,@Param("grade") Integer grade);
    public Integer getCourseMajorCount(@Param("semesterId") Integer semesterId,@Param("cno") Integer cno);
    public Integer checkCourseMajor(@Param("semesterId") Integer semesterId,@Param("cno") String cno,@Param("majorId") Integer majorId,@Param("grade") Integer grade);
    public List<MobileSchedulingView> getCoursesByTnoAndTnameAndAddress(@Param("tno") Integer tno,@Param("tname") String tname,@Param("address") String address);
    public List<SchedulingCourseView> getAllCourses(@Param("year") String year,@Param("semester") String semester,
                                                    @Param("majorName") String majorName,@Param("grade") Integer grade);

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

    /**
     * @author: yuzhongrui
     *
     */
    public List<TeaCourseView> getCourseInfoByTno(int tno);

}
