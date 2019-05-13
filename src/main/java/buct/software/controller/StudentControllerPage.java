package buct.software.controller;

import buct.software.domain.Student;
import buct.software.domain.User;
import buct.software.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class StudentControllerPage {
    @Autowired
    StudentService studentService;
    public boolean checkPower(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return false;
        }
        return user.getType() == 2;
    }
    @RequestMapping("/StudentsInfo")
    public String studentsIofo(HttpServletRequest request) {
        if (checkPower(request) == false) {
            return "error";
        }
        List<Student> allStudent = studentService.getAllStudent();
        request.getSession().setAttribute("allStudent", allStudent);
        return "redirect:/StudensPage";
    }

    @RequestMapping("/StudensPage")
    public String studensPage(HttpServletRequest httpServletRequest) {
        if (checkPower(httpServletRequest) == false) {
            return "error";
        }
        Object allStudent = httpServletRequest.getSession().getAttribute("allStudent");
        System.out.println(allStudent);
        httpServletRequest.setAttribute("allStudent", allStudent);
        return "StudentsInfo";
    }

    @RequestMapping("/StudentsAddAction")
    public String studentsAdd(HttpServletRequest httpServletRequest) {
        if (checkPower(httpServletRequest) == false) {
            return "error";
        }
        httpServletRequest.setAttribute("needUpdateStudent", null);
        return "StudentsAdd";
    }

    @PostMapping("/DoStudentsAdd")
    public String doStudentsAdd(@RequestParam("sno") Integer sno,
                                @RequestParam("sex") String sex,
                                @RequestParam("sname") String sname,
                                @RequestParam("major") String major,
                                @RequestParam("klass") String klass,
                                @RequestParam("comeYear") String comeYear,
                                @RequestParam("phone") String phone,
                                @RequestParam("grade") String grade,
                                @RequestParam("college") String college,
                                @RequestParam("collegeId") Integer collegeId,
                                @RequestParam("majorId") Integer majorId,HttpServletRequest request) {
        if (checkPower(request) == false) {
            return "error";
        }
        System.out.println(phone);
        Student student = new Student(sno, sname, sex, major, klass, comeYear, phone, college, collegeId, grade, majorId);
        if (studentService.getStudentBySno(sno) == null) {
            studentService.saveStudent(student);
        } else {
            studentService.updateStudent(student);
        }
        return "forward:/StudentsInfo";
    }


    @PostMapping("/SearchStudents")
    public String searchStudents(HttpServletRequest httpServletRequest) {
        if (checkPower(httpServletRequest) == false) {
            return "error";
        }
        Student student = new Student();
        Integer sno = null;
        try {
            sno = (httpServletRequest.getParameter("sno").equals("")) ? null : Integer.parseInt(httpServletRequest.getParameter("sno"));
        } catch (Exception e) {
            return "error";
        }
        String sname = (httpServletRequest.getParameter("sname").equals("")) ? null : httpServletRequest.getParameter("sname");
        String major = (httpServletRequest.getParameter("major").equals("")) ? null : httpServletRequest.getParameter("major");
        String grade = (httpServletRequest.getParameter("grade").equals("")) ? null : httpServletRequest.getParameter("grade");
        student.setSno(sno);
        student.setSname(sname);
        student.setGrade(grade);
        student.setMajor(major);
        List<Student> studentsByExample = studentService.getStudentsByExample(student);
        System.out.println(studentsByExample);
        httpServletRequest.getSession().setAttribute("allStudent", studentsByExample);
        return "forward:/StudensPage";
    }

    @RequestMapping("/UpdateStudent")
    public String updateStudent(Map<String, Object> paramMap,HttpServletRequest httpServletRequest) {
        if (checkPower(httpServletRequest) == false) {
            return "error";
        }
        String sno = httpServletRequest.getParameter("sno");
        int sno_int = Integer.parseInt(sno);
        Student student = studentService.getStudentBySno(sno_int);
        System.out.println(student);
        System.out.println("test");
        paramMap.put("needUpdateStudent", student);
        return "StudentsAlter";
    }

}
