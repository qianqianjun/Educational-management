package buct.software.dao;

import buct.software.views.PlanningView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanningDao {
    public List<PlanningView> getWillCourses(String year,String semester,String majorName,Integer grade);
    public Boolean addWillCourse(Integer semesterId,Integer majorId,Integer grade,Integer cno);
    public Boolean checkWillCourse(Integer semesterId,Integer majorId,Integer grade,Integer cno);
    public Boolean deleteWillCourse(Integer semesterId,Integer majorId,Integer grade,Integer cno);
    public Boolean checkIfOpen(Integer semesterId,Integer majorId,Integer grade,Integer cno);
    public Boolean changeIfOpen(Integer semesterId,Integer majorId,Integer grade,Integer cno,
                                Boolean ifOpen);
}
