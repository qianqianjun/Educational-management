package buct.software.dao;

import buct.software.domain.Scheduling;
import buct.software.views.SchedulingView;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 高谦
 * 排课表 scheduling 数据库操作类
 */
@Repository
public interface SchedulingDao {
    public SchedulingView getCourse(Integer semesterId,String cno);
    public Boolean deleteCourse(Integer semesterId,String cno);
    public Boolean deleteCourseMajor(Integer semesterId,String cno,Integer majorId,String grade);
    public Boolean addCourse(Integer semesterId,String cno,String tno,
                             String status,Integer capacity,String address,String time);
    public Boolean addCourseMajor(Integer semesterId,String cno,Integer majorId,String grade);
    public Integer getCourseMajorCount(Integer semesterId,String cno);
    public Integer checkCourseMajor(Integer semesterId,String cno,Integer majorId,String grade);
}
