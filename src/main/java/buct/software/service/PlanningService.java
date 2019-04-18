package buct.software.service;

import buct.software.dao.CourseDao;
import buct.software.dao.MajorDao;
import buct.software.dao.PlanningDao;
import buct.software.dao.SemesterDao;
import buct.software.utils.ResponseMessage;
import buct.software.views.PlanningView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanningService {
    @Autowired
    PlanningDao planningDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    SemesterDao semesterDao;
    @Autowired
    MajorDao majorDao;

    public ResponseMessage getWillCourses(String year,String semester,
                                          String majorName,String grade){
        List<PlanningView> data = planningDao.getWillCourses(year,semester,majorName,grade);
        ResponseMessage message=ResponseMessage.getMessage(data!=null,ResponseMessage.SUCCESS,
                "查询成功！", ResponseMessage.EmptyDate,"查询结果为空！");
        message.setData(data);
        return message;
    }

    public ResponseMessage addWillCourse(Integer cno,String cname,String college,
                                         String description,String status,String year,
                                         String semester,String majorName,String grade){
        Boolean flag = courseDao.checkCourse(cno);
        if(flag == false){
            courseDao.addCourse(cno,cname,college,description,status);
        }
        Integer semesterId = semesterDao.getSemesterId(year,semester);
        Integer majorId = majorDao.getMajorIdByMajorName(majorName);
        Boolean data = false;
        if(planningDao.checkWillCourse(semesterId,majorId,grade,cno)==false){
            planningDao.addWillCourse(semesterId,majorId,grade,cno);
            data = true;
        }
        ResponseMessage message=ResponseMessage.getMessage(data!=false,ResponseMessage.SUCCESS,
                "插入成功！", ResponseMessage.WRONG,"插入失败！");
        message.setData(data);
        return message;
    }

    // 这里修改了一下  高谦
    public ResponseMessage deleteWillCourse(Integer cno,String year,String semester,
                                            String majorName,String grade){
        Integer semesterId = semesterDao.getSemesterId(year,semester);
        Integer majorId = majorDao.getMajorIdByMajorName(majorName);
        planningDao.deleteWillCourse(semesterId,majorId,grade,cno);
        Boolean data = !planningDao.checkWillCourse(semesterId,majorId,grade,cno);
        ResponseMessage message=ResponseMessage.getMessage(data!=false,ResponseMessage.SUCCESS,
                "删除成功！", ResponseMessage.WRONG,"删除失败！");
        message.setData(data);
        return message;
    }
}
