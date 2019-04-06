package buct.software.controller;
import buct.software.domain.SelectCourse;
import buct.software.domain.Student;
import buct.software.service.CollegeService;
import buct.software.service.SelectCourseService;
import buct.software.service.StudentService;
import buct.software.utils.ResponseMessage;
import buct.software.views.SelectCourseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author  高谦  第一组任务代码
 * 学生选课子系统 controller。
 */
@Controller
public class SelectCourseController {
    @Autowired
    CollegeService collegeService;
    @Autowired
    SelectCourseService selectCourseService;
    @Autowired
    StudentService studentService;

    /**
     * 展示选课界面
     * @param request  HttpServletRequest 参数
     * @param parmMap  发给前端的参数集合
     * @return
     */
    @GetMapping("/selectcourse")

    public Object selectCourse(HttpServletRequest request, Map<String,Object> parmMap){
//        HttpSession session =request.getSession();
//        User user=(User) session.getAttribute("user");
//        Integer sno=user.getAccount();
        Integer sno=2016014302;
        Student student=studentService.getStudentBySno(sno);
        ArrayList<SelectCourse> selectedList=
                (ArrayList<SelectCourse>) selectCourseService.getSelectedCourseList(sno);
        ArrayList<SelectCourseView> courseViews=
                (ArrayList<SelectCourseView>) selectCourseService.getAllCourseList();
        parmMap.put("courselist",courseViews);
        parmMap.put("selectedlist",selectedList);
        return "selectcourse";
    }

    @PostMapping("/filtercourse")
    public ResponseMessage filterCourse(@RequestParam("college") String college,
                               @RequestParam("capacity") String capacity,
                               @RequestParam("cno") String cno,
                               @RequestParam("cname") String cname,
                               @RequestParam("tname") String tname){
        ArrayList<SelectCourseView> courseViews=
                (ArrayList<SelectCourseView>) selectCourseService.getCourseList(college,capacity,cno,cname,tname);
        return null;
    }

    /**
     * 返回所有学院的 Controller 接口。
     * @return
     */
    @ResponseBody
    @GetMapping("/getcolleges")
    public ResponseMessage getCollege(){
        return collegeService.getAllCollege();
    }

    /**
     * 根据学院id 返回学院相关信息的接口
     * @param id  学院的id
     * @return  返回的是
     */
    @ResponseBody
    @PostMapping("/getcollegebyid")
    public ResponseMessage getCollegeById(@RequestParam String id){
        return collegeService.getCollegeById(id);
    }

}
