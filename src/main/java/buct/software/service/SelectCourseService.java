package buct.software.service;
import buct.software.dao.SelectCourseDao;
import buct.software.domain.ScheduleMajor;
import buct.software.domain.SelectCourse;
import buct.software.views.SelectCourseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author  高谦
 * 选课子系统服务层
 */

@Service
public class SelectCourseService {
    @Autowired
    SelectCourseDao selectCourseDao;

    public List<SelectCourseView> getAllCourseList(Integer semesterId,Integer majorId){
        ScheduleMajor parms=new ScheduleMajor();
        parms.setMajorId(majorId);
        parms.setSemesterId(semesterId);
        return selectCourseDao.getAllAvaiableCourse(parms);
    }

    /**
     *  查询函数，用于作为查询的条件
     * @param college  开课学院
     * @param capacity  容量 ：这里设置的是是否显示没有余量的课程。
     * @param cno  课程号
     * @param cname   课程名称
     * @param tname  老师名称
     * @return
     */
    public List<SelectCourseView> getCourseList(String college,String capacity,
                                                String cno,String cname,String tname){
        SelectCourseView view=new SelectCourseView();
        if(!capacity.equals(""))
            view.setCapacity(Integer.parseInt(capacity));
        if (!cname.equals(""))
            view.setCname(cname);
        if(!cno.equals(""))
            view.setCno(Integer.parseInt(cno));
        if(!college.equals(""))
            view.setCollege(college);
        if(!tname.equals(""))
            view.setTname(tname);
        return selectCourseDao.getAllAvaiableCourseWithCondition(view);
    }

    /**
     * 根据学号获取一个学生的已选课程的信息。
     * @param sno 学生学号
     * @param semesterId  学期id
     * @return
     */
    public List<SelectCourse> getSelectedCourseList(Integer sno,Integer semesterId){
        SelectCourse parma = new SelectCourse();
        parma.setSno(sno);
        parma.setSemesterId(semesterId);
        return selectCourseDao.getAllCourseHaveBeenSelected(parma);
    }
}