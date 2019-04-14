package buct.software.controller;
import buct.software.domain.Student;
import buct.software.domain.Teacher;
import buct.software.domain.User;
import buct.software.service.StudentService;
import buct.software.service.TeacherService;
import buct.software.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 高谦
 *  登录功能实现
 *  特别注意，这里不可以配置拦截。
 *  网站如果登录页面都需要已登录的用户才可以看到的话，那就很荒谬了。
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    /**
     * 登录页面网址，请求这个地址用于展现登录页面
     * 当然，如果已经有登陆信息的话，会直接跳转到登录成功的界面。
     * @param request  这里是 一个HttpServletRequest 用于获取 session 相关信息。
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        HttpSession session=request.getSession();
        Object userInfo=session.getAttribute("user");
        if(userInfo==null){
            return "login";
        }
        else{
            User user=(User) userInfo;
            Integer type=user.getType();
            if(type==0){
                return "student";
            }
            if(type==1){
                return "teacher";
            }
            else{
                return "admin";
            }
        }
    }

    /**
     *  这是点击登录按钮之后的处理函数: 查数据库，匹配密码等。
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request,
                        Map<String,Object> paraMap,
                        @RequestParam("account") String account,
                        @RequestParam("password") String password){
        HttpSession session=request.getSession();
        User user=userService.LoginFun(account,password);
        if(user==null){
            paraMap.put("error_msg","用户名或者密码错误，请重新输入");
            return "login";
        }
        else{
            // 查询详细保存在session 中，也就是说登录的是一个学生的话，
            // 还要保存学生的信息，如果是一个老师，要保存一个老师的信息
            boolean error=false;
            if(user.getType()==0){
                // 是一个学生
                Student student=studentService.getStudentBySno(user.getAccount());
                if(student!=null) {
                    user.setMajor(student.getMajor());
                    user.setSname(student.getSname());
                    user.setMajorid(student.getMajorId());
                }else error=true;
            }
            else if(user.getType()==1){
                // 是一个老师
                Teacher teacher=teacherService.getTeacherByTno(user.getAccount());
                if(teacher!=null) {
                    user.setTname(teacher.getTname());
                }
                else{
                    error=true;
                }
            }
            else if(user.getType()==2){
                // 这是管理员
                Teacher teacher=teacherService.getTeacherByTno(user.getAccount());
                if(teacher!=null){
                    user.setTname(teacher.getTname());
                }
                else{
                    error=true;
                }
            }
            if(error){
                paraMap.put("error_msg","数据库中找不到您的详细信息，请联系管理员");
                return "login";
            }
            else {
                session.setAttribute("user", user);
                return "redirect:/index";
            }
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.setAttribute("user",null);
        return "redirect:/index";
    }

}