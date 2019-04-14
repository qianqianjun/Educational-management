package buct.software.controller;
import buct.software.domain.SelectCourse;
import buct.software.domain.User;
import buct.software.service.CollegeService;
import buct.software.service.SelectCourseService;
import buct.software.service.SemesterService;
import buct.software.views.SelectCourseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
        Integer semesterId= 0; //semesterService.getCurrentSemesterId();
        ArrayList<SelectCourse> selectedList=
                (ArrayList<SelectCourse>) selectCourseService.getSelectedCourseList(sno,semesterId);


        System.out.println("这里完成了");
        ArrayList<SelectCourseView> courseViews=
                (ArrayList<SelectCourseView>)
                        selectCourseService.getAllCourseList(semesterId,majorId);
        parmMap.put("courseselectedlist",selectedList);
        parmMap.put("allcourses",courseViews);
        return "selectcourse";
    }

}
