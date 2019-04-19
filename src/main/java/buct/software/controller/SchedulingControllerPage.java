package buct.software.controller;
import buct.software.domain.User;
import buct.software.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 刘权达
 * 排课子系统的关于页面模板初始化的controller类。
 */
@Controller
public class SchedulingControllerPage {
//    @Autowired
//    CollegeService collegeService;
//    @Autowired
//    SelectCourseService selectCourseService;



    @GetMapping("/classScheduling")
    public String classScheduling(Map<String,Object> parmMap, HttpServletRequest request){
        HttpSession session =request.getSession();
        User user=(User) session.getAttribute("user");
        Integer sno=user.getAccount();
        Integer majorId=user.getMajorid();
        System.out.println("success");
        /**
         * 这里需要判断哪些老师有权限排课，属于后期功能
         * 暂且不表
         */
        return "classscheduling";
    }

    @GetMapping("/schedulingForm")
    public String schedulingForm(Map<String,Object> parmMap, HttpServletRequest request){
        HttpSession session =request.getSession();
        User user=(User) session.getAttribute("user");
        Integer sno=user.getAccount();
        Integer majorId=user.getMajorid();
        System.out.println("success");
        /**
         * 这里需要判断哪些老师有权限排课，属于后期功能
         * 暂且不表
         */
        return "schedulingform";
    }

}
