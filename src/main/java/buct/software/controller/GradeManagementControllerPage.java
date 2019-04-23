package buct.software.controller;

import buct.software.domain.Course;
import buct.software.domain.Semester;
import buct.software.domain.User;
import buct.software.service.GradeManagementService;
import buct.software.service.PowerService;
import buct.software.service.SemesterService;
import buct.software.views.GradeManagementView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

@Controller
public class GradeManagementControllerPage {
    @Autowired
    GradeManagementService gradeManagementService;
    @Autowired
    SemesterService semesterService;
    @Autowired
    PowerService powerService;

//    学生端PC
    @RequestMapping ("/gradeinfo")
    public String gradeManagement(Map<String,Object> parMap,HttpServletRequest request){
        HttpSession session =request.getSession();
        User user=(User) session.getAttribute("user");
        Integer sno=user.getAccount();
        Semester semester=semesterService.getCurrentSemesterInfo();
        List<GradeManagementView> studentGradeList= gradeManagementService.getCourseGrade(semester.getStart(),semester.getSemester(),sno);
        parMap.put("gradetable",studentGradeList);
        parMap.put("currentSemester",semester);
        for(GradeManagementView temp:studentGradeList){
            temp.getCname();
        }
        return "GradeManagementStudent";
    }

    //    学生端移动
    @RequestMapping ("/gradeinfomobile")
    public String gradeManagementm(Map<String,Object> parMap,HttpServletRequest request){
        HttpSession session =request.getSession();
        User user=(User) session.getAttribute("user");
        Integer sno=user.getAccount();
        Semester semester=semesterService.getCurrentSemesterInfo();
        List<GradeManagementView> studentGradeList= gradeManagementService.getCourseGrade(semester.getStart(),semester.getSemester(),sno);
        parMap.put("gradetable",studentGradeList);
        parMap.put("currentSemester",semester);
        for(GradeManagementView temp:studentGradeList){
            temp.getCname();
        }
        return "GradeStudentM";
    }


    //    教师端PC
    @RequestMapping ("/gradeinfoteacher")
    public String gradeManagementTeacher(Map<String,Object> parMap,HttpServletRequest request){
        HttpSession session =request.getSession();
        User user=(User) session.getAttribute("user");
        Integer tno=user.getAccount();
        boolean permission = powerService.getScore();//获取选课权限
        Semester semester=semesterService.getCurrentSemesterInfo();

        parMap.put("currentSemester",semester);
        parMap.put("permission",permission);


        return "try";
    }

    //    教师端移动
    @RequestMapping ("/gradeteachermobile")
    public String gradeManagementTeacherm(Map<String,Object> parMap,HttpServletRequest request){
        HttpSession session =request.getSession();
        User user=(User) session.getAttribute("user");
        Integer tno=user.getAccount();

        boolean permission = powerService.getScore();//获取选课权限
        Semester semester=semesterService.getCurrentSemesterInfo();

        parMap.put("currentSemester",semester);
        parMap.put("permission",permission);

        return "GradeTeacherM";
    }

}

