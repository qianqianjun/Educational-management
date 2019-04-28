package buct.software.service;

import buct.software.dao.SchedulingDao;
import buct.software.domain.Scheduling;
import buct.software.domain.SelectCourse;

import buct.software.domain.User;
import buct.software.utils.CourseDataUtil;
import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.synth.SynthCheckBoxMenuItemUI;

import java.util.List;

/**
 * @author 高谦
 * 用于检测冲突的 服务：包括排课系统的冲突和选课系统的冲突
 * 表示上课的时间字符串：
 * (startweek-endweek,weekday,coursestarttime-courseendtime);(startweek-endweek,weekday,coursestarttime-courseendtime)
 * 例如： (1-4,5,1-3);(1-4,2,3-5);(5-10,1,6-8)
 */
@Service
public class ConflictService {
    @Autowired
    SchedulingDao schedulingDao;
    /**
     * 查询当前学期，当前老师，当前时间下，是否已经安排了任务
     * @param semesterId  当前学期的id
     * @param tno  当前老师的工号
     * @param timeStr  表示要插入数据库的时间的格式串（采用刘权达大佬的超级无敌时间表示法）
     *                 "(1-4,5,1-3);(1-4,2,3-5);(5-10,1,6-8)"
     * @return 如果已经安排了任务，就返回false，表示不可以再安排
     * 如果没有任务，就返回true 表示可以安排任务。
     */
    public Boolean teacher(Integer semesterId,Integer tno,String timeStr){
        if(semesterId==null || tno==null || timeStr==null)
            return false;

        Scheduling scheduling=new Scheduling();
        scheduling.setSemesterId(semesterId);
        scheduling.setTno(tno);
        List<String> courseTimeStrList=schedulingDao.getTeacherTaskTime(scheduling);

        return !CourseDataUtil.isConflict(timeStr,courseTimeStrList);
    }

    /**
     * 检查学生的时间是不是冲突，和上面的是一样的。
     * @param semesterId
     * @param sno
     * @param timeStr
     * @return  详细见 teacher 的注释
     */
    public Boolean student(Integer semesterId,Integer sno,String timeStr){
        if(semesterId ==null || sno==null || timeStr==null)
            return false;
        SelectCourse selectCourse=new SelectCourse();
        selectCourse.setSemesterId(semesterId);
        selectCourse.setSno(sno);
        List<String> courseTimeStrList=schedulingDao.getStudentTaskTime(selectCourse);

        return !CourseDataUtil.isConflict(timeStr,courseTimeStrList);
    }

    /**
     * 用于检测教室使用是不是有冲突
     * @param semesterId  学期id
     * @param room  教室的名称 比如 A-103
     * @return  详见 teacher 的注释
     */
    public Boolean classRoom(Integer semesterId,String room,String timeStr){
        if(semesterId!=null || room!=null || timeStr!=null)
            return false;
        Scheduling scheduling=new Scheduling();
        scheduling.setSemesterId(semesterId);
        scheduling.setAddress(room);
        List<String> courseTimeStrList=schedulingDao.getRoomTaskTime(scheduling);

        return !CourseDataUtil.isConflict(timeStr,courseTimeStrList);
    }

}
