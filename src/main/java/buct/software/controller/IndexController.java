package buct.software.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author  高谦
 * 这是一个索引页面，用于用户提交登录后到达指定的页面
 * 现在分为教师端和学生端两个界面，两个接口没有登录的情况下都会被拦截
 */
@Controller
public class IndexController {
    @RequestMapping("/tcontent")
    public String tcontent(){
        return "teacher_content";
    }

    @RequestMapping("/scontent")
    public String scontent(){
        return "stu_content";
    }
}