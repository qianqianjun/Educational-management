package buct.software.controller;

import buct.software.domain.Semester;
import buct.software.domain.Student;
import buct.software.domain.Teacher;
import buct.software.domain.User;
import buct.software.service.PowerService;
import buct.software.service.SemesterService;
import buct.software.service.StudentService;
import buct.software.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class MobileBackstageControllerPage {
    @Autowired
    PowerService powerService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    SemesterService semesterService;

    @RequestMapping("/GoMobileHomePage")
    public String goMobileHomePage(HttpServletRequest httpServletRequest,Map map) {
        User user =(User) httpServletRequest.getSession().getAttribute("user");
        map.put("name",user.getTname());
        System.out.println(user);
        Semester currentSemesterInfo = semesterService.getCurrentSemesterInfo();
//        currentSemesterInfo.get
        map.put("start",currentSemesterInfo.getStart());
        map.put("end",currentSemesterInfo.getEnd());
        map.put("semester",currentSemesterInfo.getSemester());
        return "mobileHomePage";
    }

    @RequestMapping("/GoPowerInfo")
    public String goPowInfo(HttpServletRequest httpServletRequest) {
        httpServletRequest.setAttribute("abnormal", powerService.getAbnormal());
        httpServletRequest.setAttribute("score", powerService.getScore());
        httpServletRequest.setAttribute("selectCourse", powerService.getSelectCourse());
        return "powInfo";
    }

    /**
     * @author 高谦修改
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/GoStudentInfo")
    public String goStudentInfo(HttpServletRequest request,Map<String,Object> map) {
        List<Student> allStudent = studentService.getAllStudent();
//        request.getSession().setAttribute("allStudent", allStudent);
        map.put("allStudent",allStudent);
        System.out.println(allStudent.size());
        return "stuInfo";
//        return "forward:/mStudentPage";
    }

    @RequestMapping("/mStudentPage")
    public String studentPage(Map<String, Object> map, HttpServletRequest request) {
        System.out.println();
        List<Student> allStudent = (List<Student>) request.getSession().getAttribute("allStudent");
//        request.setAttribute("allStudent", allStudent);
        map.put("allStudent", allStudent);
//        System.out.println(allStudent);
        return "stuInfo";
    }

    @RequestMapping("/GoTeachearInfo")
    public String goTeacherInfo(HttpServletRequest request) {
        List<Teacher> allTeacher = teacherService.getAllTeacher();
        request.getSession().setAttribute("allTeacher", allTeacher);
        return "forward:/mTeacherPage";
    }

    @RequestMapping("/mTeacherPage")
    public String teacherPage(Map<String, Object> map, HttpServletRequest request) {
        List<Teacher> allTeacher = (List<Teacher>) request.getSession().getAttribute("allTeacher");
        map.put("allTeacher", allTeacher);
        request.setAttribute("allTeacher", allTeacher);
        System.out.println(allTeacher);
        return "teaInfo";
    }

    @RequestMapping("/teacherSearch")
    public String teacherSearch(Map<String, Object> map, HttpServletRequest request) {
        return "teaSearch";
    }

    @RequestMapping("/studentSearch")
    public String studentSearch(Map<String, Object> map, HttpServletRequest request) {
        return "stuSearch";
    }

    @RequestMapping("/mobileSearchStudent")
    public String mobileSearchStudent(HttpServletRequest request) {
        String snoString = request.getParameter("sno");
        Integer sno = null;
        try {
            sno = Integer.parseInt(snoString);
        } catch (Exception e) {
            return "error";
        }
        Student student = new Student();
        student.setSno(sno);
        List<Student> studentsByExample = studentService.getStudentsByExample(student);
        request.getSession().setAttribute("allStudent", studentsByExample);
        return "forward:/mStudentPage";
    }

    @RequestMapping("/mobileSearchTeacher")
    public String mobileSearchTeacher(HttpServletRequest request) {
        String tname = request.getParameter("tname");
        Teacher teacher = new Teacher();
        teacher.setTname(tname);
        List<Teacher> teacherByExample = teacherService.getTeacherByExample(teacher);
        request.getSession().setAttribute("allTeacher", teacherByExample);
        return "forward:/mTeacherPage";
    }

    @RequestMapping("/addSemester")
    public String addSemester(HttpServletRequest request) {
//        Object start = request.getAttribute("s_year")
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String semester = request.getParameter("semester");
        String id = request.getParameter("id");
        Integer semesterId = null;
        try {
            semesterId = Integer.parseInt(id);
        } catch (Exception e) {
            return "error";
        }
        semesterService.addSemester(new Semester(semesterId, start, end, semester));
//        System.out.println(start);
        return "forward:/GoMobileHomePage";
    }

    @RequestMapping("/GoSemesterInfo")
    public String goSemesterInfo() {
        return "couManage";
    }

    @RequestMapping("/OpenSelectCourse")
    public String openSelectCourse() {
        powerService.openSelectCourse();
        return "forward:/GoPowerInfo";
    }

    @RequestMapping("/CloseSelectCourse")
    public String closeSelectCourse() {
        powerService.closeSelectCourse();
        return "forward:/GoPowerInfo";
    }

    @RequestMapping("/OpenScore")
    public String openScore() {
        powerService.openScore();
        return "forward:/GoPowerInfo";
    }

    @RequestMapping("/CloseScore")
    public String closeScore() {
        powerService.closeScore();
        return "forward:/GoPowerInfo";
    }

    @RequestMapping("/OpenAbnormal")
    public String openAbnormal() {
        powerService.openAbnormal();
        return "forward:/GoPowerInfo";
    }

    @RequestMapping("/CloseAbnormal")
    public String closeAbnormal() {
        powerService.closeAbnormal();
        return "forward:/GoPowerInfo";
    }
}
