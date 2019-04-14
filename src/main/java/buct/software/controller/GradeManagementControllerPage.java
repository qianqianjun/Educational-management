package buct.software.controller;

import buct.software.domain.SelectCourse;
import buct.software.domain.User;
import buct.software.service.GradeManagementService;
import buct.software.service.SemesterService;
import buct.software.views.SelectCourseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class GradeManagementControllerPage {
    @Autowired
    GradeManagementService gradeManagementService;
    @Autowired
    SemesterService semesterService;

    @RequestMapping ("/gradeinfo")
    public String gradeManagement(Map<String,Object> parmMap, HttpServletRequest request){
        HttpSession session =request.getSession();
        User user=(User) session.getAttribute("user");
        Integer sno=user.getAccount();
        Integer semesterId=0;
        ArrayList<SelectCourse> studentGradeList=
                (ArrayList<SelectCourse>) gradeManagementService.getCourseGrade(semesterId,sno);
        System.out.println(studentGradeList);
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhh");
        parmMap.put("studentGradeList",studentGradeList);
        parmMap.put("test","这里是 测试的内容");
        return "gradeinfo";
    }
}
