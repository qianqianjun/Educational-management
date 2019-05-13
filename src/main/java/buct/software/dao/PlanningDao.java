package buct.software.dao;

import buct.software.views.PlanningView;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanningDao {
    public List<PlanningView> getWillCourses(@Param("year") String year,@Param("semester") String semester,@Param("majorName") String majorName,@Param("grade") Integer grade);
    public Boolean addWillCourse(@Param("semesterId") Integer semesterId,@Param("majorId") Integer majorId,@Param("grade") Integer grade,@Param("cno") Integer cno);
    public Boolean checkWillCourse(@Param("semesterId") Integer semesterId,@Param("majorId") Integer majorId,@Param("grade") Integer grade,@Param("cno") Integer cno);
    public Boolean deleteWillCourse(@Param("semesterId") Integer semesterId,@Param("majorId") Integer majorId,@Param("grade") Integer grade,@Param("cno") Integer cno);
    public Boolean checkIfOpen(@Param("semesterId") Integer semesterId,@Param("majorId") Integer majorId,@Param("grade") Integer grade,@Param("cno") Integer cno);
    public Boolean changeIfOpen(@Param("semesterId") Integer semesterId,@Param("majorId") Integer majorId,@Param("grade") Integer grade,@Param("cno") Integer cno,

                                @Param("ifOpen") Boolean ifOpen);
}
