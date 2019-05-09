package buct.software.controller;

import buct.software.domain.Student;
import buct.software.domain.Teacher;
import buct.software.domain.User;
import buct.software.service.*;
import buct.software.utils.UserAgentParser;
import buct.software.views.SelectCourseView;
import buct.software.views.StudentGradeIndexView;

import buct.software.views.TeaCourseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import java.util.Map;

/**
 * @author 高谦
 * 登录功能实现
 * 特别注意，这里不可以配置拦截。
 * 网站如果登录页面都需要已登录的用户才可以看到的话，那就很荒谬了。
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    SelectCourseService selectCourseService;
    @Autowired
    SemesterService semesterService;
    @Autowired
    SchedulingService schedulingService;
    @Autowired
    CollegeService collegeService;

    /**
     * 登录页面网址，请求这个地址用于展现登录页面
     * 当然，如果已经有登陆信息的话，会直接跳转到登录成功的界面。
     *
     * @param request 这里是 一个HttpServletRequest 用于获取 session 相关信息。
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request, Map<String, Object> parmMap) {
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("user");
        Integer semesterId = semesterService.getCurrentSemesterId();

        /**
         * 获取当前的用户使用的是什么设备。
         */
        String useragent = request.getHeader("User-Agent");
        UserAgentParser userAgentParser = new UserAgentParser(useragent);
        String platform = userAgentParser.getPlatform();

        if (userInfo == null) {
            if (platform.equals("mobile"))
                return "MobileLogin";
            else
                return "login";

        } else {
            User user = (User) userInfo;
            Integer type = user.getType();
            parmMap.put("userinfo", user);
            if (type == 0) {
                List<SelectCourseView> courseTable = selectCourseService.getCourseTable(semesterId, user.getAccount());
                List<StudentGradeIndexView> gradeList = selectCourseService.getGrade(semesterId, user.getAccount());
                parmMap.put("courseTable", courseTable);
                parmMap.put("gradeList", gradeList);

                if (platform.equals("mobile"))
                    return "msh";

                return "student";
            }
            if (type == 1) {
                int tno = user.getAccount();
                List<TeaCourseView> teaCourseViews = schedulingService.getCourseInfoByTno(tno);
                session.setAttribute("CourseTable",teaCourseViews);

                parmMap.put("courseTable", teaCourseViews);//课表
                Teacher teacher = teacherService.getTeacherByTno(tno);
                parmMap.put("teainfo", teacher);
                String cid = teacher.getCollegeId().toString();
                String colname = collegeService.getColnameById(cid);
                parmMap.put("colname", colname);
                if (platform.equals("mobile"))
                    return "MobileTeacherHome";
                return "teacher";
            }
            if(type==2){
                if (platform.equals("mobile")) {
                    return "redirect:/GoMobileHomePage";
                } else {
                    return "redirect:/GoHomePage";
                }
            }
            return "login";
        }
    }

    /**
     * 这是点击登录按钮之后的处理函数: 查数据库，匹配密码等。
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request,
                        Map<String, Object> paraMap,
                        @RequestParam("account") String account,
                        @RequestParam("password") String password) {


        /**
         * 获取当前的用户使用的是什么设备。
         */
        String useragent = request.getHeader("User-Agent");
        UserAgentParser userAgentParser = new UserAgentParser(useragent);
        String platform = userAgentParser.getPlatform();



        HttpSession session=request.getSession();
        User user=userService.LoginFun(account,password);
        if(user==null){
            paraMap.put("error_msg","用户名或者密码错误，请重新输入");


            if (platform.equals("mobile"))
                return "MobileLogin";

            return "login";
        } else {
            // 查询详细保存在session 中，也就是说登录的是一个学生的话，
            // 还要保存学生的信息，如果是一个老师，要保存一个老师的信息
            boolean error = false;
            if (user.getType() == 0) {
                // 是一个学生
                Student student = studentService.getStudentBySno(user.getAccount());
                if (student != null) {
                    user.setMajor(student.getMajor());
                    user.setSname(student.getSname());
                    user.setMajorid(student.getMajorId());
                    user.setCollege(student.getCollege());
                    user.setKlass(student.getKlass());

                } else error = true;
            } else if (user.getType() == 1) {
                // 是一个老师
                Teacher teacher = teacherService.getTeacherByTno(user.getAccount());
                if (teacher != null) {
                    user.setTname(teacher.getTname());
                } else {
                    error = true;
                }
            } else if (user.getType() == 2) {
                // 这是管理员
                Teacher teacher = teacherService.getTeacherByTno(user.getAccount());
                if (teacher != null) {
                    user.setTname(teacher.getTname());
                } else {
                    error = true;
                }
            }
            if (error) {
                paraMap.put("error_msg", "数据库中找不到您的详细信息，请联系管理员");

                if (platform.equals("mobile"))
                    return "MobileLogin";

                return "login";
            } else {
                session.setAttribute("user", user);
                return "redirect:/index";
            }
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        return "redirect:/index";
    }
}


//Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36
//Mozilla/5.0 (Linux; Android 7.0; JMM-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Mobile Safari/537.36
//Mozilla/5.0 (iPhone; CPU iPhone OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.1 Mobile/15E148 Safari/604.1


