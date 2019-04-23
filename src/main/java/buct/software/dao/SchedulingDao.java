package buct.software.dao;
import buct.software.domain.Scheduling;
import buct.software.views.SchedulingView;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author 高谦
 * 排课表 scheduling 数据库操作类
 * @implement 实现者名字
 */
@Repository
public interface SchedulingDao {
    public SchedulingView getCourse(Integer semesterId,Integer cno);
    public Boolean deleteCourse(Integer semesterId,Integer cno);
    public Boolean deleteCourseMajor(Integer semesterId,Integer cno,Integer majorId,String grade);
    public Boolean addCourse(Integer semesterId,Integer cno,Integer tno,
                             String status,Integer capacity,String address,String time);
    public Boolean addCourseMajor(Integer semesterId,Integer cno,Integer majorId,String grade);
    public Integer getCourseMajorCount(Integer semesterId,Integer cno);
    public Integer checkCourseMajor(Integer semesterId,String cno,Integer majorId,String grade);

    //几个用于检测冲突的查询方法

    /**
     * 获取教师(学生)在这个学期已经安排好的任务的时间字符串列表
     * @param semesterId  当前学期id
     * @param tno  老师的工号
     * @return  返回老师的任务时间列表
     */
    public List<String> getTeacherTaskTime(Integer semesterId,Integer tno);
    public List<String> getStudentTaskTime(Integer semesterId,Integer sno);

    /**
     * 查询某一个教室的所有使用时间。
     * @param semesterId  学期id
     * @param room  教室的名称 （也就是排课表中的位置）
     * @return
     */
    public List<String> getRoomTaskTime(Integer semesterId,String room);

}
