package buct.software.service;

import buct.software.dao.MajorDao;
import buct.software.dao.PlanningDao;
import buct.software.dao.SchedulingDao;
import buct.software.dao.SemesterDao;
import buct.software.domain.Scheduling;
import buct.software.utils.ResponseMessage;
import buct.software.views.SchedulingView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘权达
 * 数据表 scheduling 服务层
 * 未完成
 */
@Service
public class SchedulingService {
    @Autowired
    SchedulingDao schedulingDao;
    @Autowired
    PlanningDao planningDao;
    @Autowired
    SemesterDao semesterDao;
    @Autowired
    MajorDao majorDao;

    public ResponseMessage getCourse(String cno,String year,String semester,
                                     String majorName,String grade){
        Integer semesterId = semesterDao.getSemesterId(year,semester);
        Integer majorId = majorDao.getMajorIdByMajorName(majorName);
        //判断是否已排
        Boolean flag = planningDao.checkIfOpen(semesterId,majorId,grade,cno);
        SchedulingView schedulingView = new SchedulingView(null,null,null,null,null);
        if(flag){
            schedulingView=schedulingDao.getCourse(semesterId,cno);
        }
        ResponseMessage message=ResponseMessage.getMessage(schedulingView!=null,ResponseMessage.SUCCESS,
                "查找成功！", ResponseMessage.WRONG,"查找失败！");
        message.setData(schedulingView);
        return message;
    }

    public ResponseMessage addCourse(String cno,String tno,String year,String semester,
                                     String majorName,String grade,String status,
                                     String capacity1,String address,String time){
        Integer capacity=Integer.valueOf(capacity1);
        Integer semesterId = semesterDao.getSemesterId(year,semester);
        Integer majorId = majorDao.getMajorIdByMajorName(majorName);
        schedulingDao.deleteCourse(semesterId,cno);
        schedulingDao.addCourse(semesterId,cno,tno,status,capacity,address,time);
        schedulingDao.deleteCourseMajor(semesterId,cno,majorId,grade);
        schedulingDao.addCourseMajor(semesterId,cno,majorId,grade);
        planningDao.changeIfOpen(semesterId,majorId,grade,cno,true);
        SchedulingView schedulingView=schedulingDao.getCourse(semesterId,cno);
        ResponseMessage message=ResponseMessage.getMessage(schedulingView!=null,ResponseMessage.SUCCESS,
                "插入成功！", ResponseMessage.WRONG,"插入失败！");
        message.setData(schedulingView);
        return message;
    }

    public ResponseMessage deleteCourse(String cno,String year,String semester,
                                        String majorName,String grade){
        Integer semesterId = semesterDao.getSemesterId(year,semester);
        Integer majorId = majorDao.getMajorIdByMajorName(majorName);
        Integer count1 = schedulingDao.getCourseMajorCount(semesterId,cno);
        schedulingDao.deleteCourseMajor(semesterId,cno,majorId,grade);
        planningDao.changeIfOpen(semesterId,majorId,grade,cno,false);
        Integer count2 = schedulingDao.getCourseMajorCount(semesterId,cno);
        if(count2==0){
            schedulingDao.deleteCourse(semesterId,cno);
        }
        Boolean flag=false;
        if(count1!=count2){
            flag=true;
        }
        ResponseMessage message=ResponseMessage.getMessage(flag!=false,ResponseMessage.SUCCESS,
                " 删除成功！", ResponseMessage.WRONG,"删除失败！");
        message.setData(flag);
        return message;
    }
}
