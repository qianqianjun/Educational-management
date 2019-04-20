package buct.software.controller;
import buct.software.domain.College;
import buct.software.domain.SelectCourse;
import buct.software.domain.Semester;
import buct.software.domain.User;
import buct.software.service.CollegeService;
import buct.software.service.SelectCourseService;
import buct.software.service.SemesterService;
import buct.software.utils.ResponseMessage;
import buct.software.views.SelectCourseView;
import org.apache.poi.hssf.record.ObjRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.soap.SAAJMetaFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 高谦
 * 学生选课子系统的关于页面模板初始化的controller类。
 */
@Controller
public class SelectCourseControllerPage {
    @Autowired
    CollegeService collegeService;
    @Autowired
    SelectCourseService selectCourseService;
    @Autowired
    SemesterService semesterService;

    /**
     * 展示选课界面，这里默认的查询所有 本专业，本学年 的课程信息。
     * @param request  HttpServletRequest 参数
     * @param parmMap  发给前端的参数集合
     * @return
     */
    @GetMapping("/selectcourse")
    public String selectCourse(Map<String,Object> parmMap, HttpServletRequest request){
        HttpSession session =request.getSession();
        User user=(User) session.getAttribute("user");
        Integer sno=user.getAccount();
        Integer majorId=user.getMajorid();
        List<Semester> semesters=semesterService.getSemesterDomain();
        Integer semesterId=semesterService.getCurrentSemesterId();
        ArrayList<SelectCourse> selectedList=
                (ArrayList<SelectCourse>) selectCourseService.getSelectedCourseList(sno,semesterId);

        ArrayList<SelectCourseView> courseViews=
                (ArrayList<SelectCourseView>)
                        selectCourseService.getAllCourseList(semesterId,majorId);

        ArrayList<College> colleges=(ArrayList<College>) collegeService.getAllCollege().getData();
        parmMap.put("courseselectedlist",selectedList);
        parmMap.put("allcourses",courseViews);
        parmMap.put("semesterlist",semesters);
        parmMap.put("colleges",colleges);
        return "selectcourse";
    }

    /**
     * 学生课表查询界面的初始化controller 接口
     * @param parMap 参数列表
     * @param request  servlet request对象
     * @return  返回初始化的界面
     */
    @GetMapping("/coursetable")
    public String getCourseTable(Map<String,Object> parMap,HttpServletRequest request){
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        Integer sno=user.getAccount();
        Integer semesterId=semesterService.getCurrentSemesterId();
        ArrayList<SelectCourseView> courseTable=(ArrayList<SelectCourseView>)
                selectCourseService.getCourseTable(semesterId,sno);
        List<Semester> semesters=semesterService.getSemesterDomain();
        parMap.put("coursetable",courseTable);
        parMap.put("semesterlist",semesters);
        return "coursetable";
    }

    @GetMapping("/selectcoursemobile")
    public String selectcoursemobile(Map<String, Object> parmMap){
        ResponseMessage res=collegeService.getAllCollege();
        List<College> collegeList=(List<College>) res.getData();
        parmMap.put("collegelist",collegeList);
        return "selectcoursemobile";
    }
    @GetMapping("/coursetablemobile")
    public String coursetablemobile(Map<String, Object> parmMap){
        List<Semester> semesters=semesterService.getSemesterDomain();
        parmMap.put("semesterlist",semesters);
        return "coursetablemobile";
    }
}
