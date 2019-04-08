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

    /**
     * 这是初始化选课界面用到的函数，实现的是请求这个学期，这个学生所在的专业可以选修的全部课程。
     * @param semesterId  学期标识
     * @param majorId  专业标识
     * @return  排课表的 view 列表
     */
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

    /**
     * 将未选课程添加到选课表中
     * @param semesterId  学期的id
     * @param sno  学号
     * @param cno  课程号
     * @return  插入成功返回插入的信息，插入失败返回 null
     */
    public SelectCourse addCourseToTable(Integer semesterId, Integer sno, Integer cno) {
        SelectCourse selectCourse=new SelectCourse();
        selectCourse.setSemesterId(semesterId);
        selectCourse.setSno(sno);
        selectCourse.setCno(cno);
        Integer accectRows=selectCourseDao.addNewCourseToTable(selectCourse);
        if(accectRows>0){
            return selectCourse;
        }
        else{
            return null;
        }
    }

    /**
     * 取消选课函数
     * @param semesterId  当前学期
     * @param cno 要取消的课程号
     * @param sno 要取消的学生
     * @return
     */
    public SelectCourse removeCourse(Integer semesterId, Integer cno, Integer sno) {
        SelectCourse selectCourse=new SelectCourse();
        selectCourse.setCno(cno);
        selectCourse.setSno(sno);
        selectCourse.setSemesterId(semesterId);
        Integer affectRows=selectCourseDao.deleteCourseFromTable(selectCourse);
        if(affectRows>0){
            return selectCourse;
        }
        else{
            return null;
        }
    }

    /**
     * 得到课程表的函数
     * @param semesterId  学期id
     * @param sno 学生学号
     * @return  学生课表
     */
    public List<SelectCourseView> getCourseTable(Integer semesterId, Integer sno) {
        SelectCourse selectCourse=new SelectCourse();
        selectCourse.setSemesterId(semesterId);
        selectCourse.setSno(sno);
        List<SelectCourseView> courseTable=selectCourseDao.getCourseTable(selectCourse);
        return courseTable;
    }
}