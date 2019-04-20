package buct.software.controller;

import buct.software.domain.Student;
import buct.software.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StudentControllerPage {
    @Autowired
    StudentService studentService;

    @RequestMapping("/StudentsInfo")
    public String studentsIofo(HttpServletRequest request) {
        List<Student> allStudent = studentService.getAllStudent();
        request.getSession().setAttribute("allStudent", allStudent);
        return "redirect:/StudensPage";
    }

    @RequestMapping("/StudensPage")
    public String studensPage(HttpServletRequest httpServletRequest) {
        Object allStudent = httpServletRequest.getSession().getAttribute("allStudent");
        System.out.println(allStudent);
        httpServletRequest.setAttribute("allStudent", allStudent);
        return "StudentsInfo";
    }

    @RequestMapping("/StudentsAdd")
    public String studentsAdd(HttpServletRequest httpServletRequest) {
        httpServletRequest.setAttribute("student",new Student());
        return "StudentsAdd";
    }

    @PostMapping("/DoStudentsAdd")
    public String doStudentsAdd(@ModelAttribute(value = "student") Student student,HttpServletRequest httpServletRequest) {
        Student student1 = (Student)httpServletRequest.getAttribute("student");
        System.out.println(student);
        System.out.println(student1);
        return "StudentsInfo";
}


    @PostMapping("/SearchStudents")
    public String searchStudents(HttpServletRequest httpServletRequest) {
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
//        System.out.println(sno);
//        System.out.println(sname);
//        System.out.println(major);
//        System.out.println(grade);
        student.setSno(sno);
        student.setSname(sname);
        student.setGrade(grade);
        student.setMajor(major);
        List<Student> studentsByExample = studentService.getStudentsByExample(student);
        System.out.println(studentsByExample);
        httpServletRequest.getSession().setAttribute("allStudent", studentsByExample);
        return "forward:/StudensPage";
    }
}
