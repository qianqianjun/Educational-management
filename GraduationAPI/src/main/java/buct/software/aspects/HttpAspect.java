package buct.software.aspects;
import buct.software.controller.IndexController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author  高谦
 * 统一的进行登录验证，如果没哟登录，则直接返回到原来的界面
 */

@Aspect
@Component
public class HttpAspect {
    /**
     * 在 CollegeController下面的所有方法 执行之前执行下面的代码块
     * 比如是否登录的判断等逻辑
     * 这就不用在每个方法中都写判断逻辑了。
     * 有bug ，真难受
     */
    /*@Autowired
    IndexController controller;
    @Pointcut("execution(public * buct.software.controller.CollegeController.*(..))")
    public void login(){
    }

    @Around("login()")
    public void islogin(ProceedingJoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        HttpSession session=request.getSession();
        if(session.getAttribute("user")==null){
            System.out.println("返回登录页面才对！");
            ModelAndView view=new ModelAndView("path:/index");
            //return view;
            //这里没写好！还有bug
        }
    }
    //这里写的是执行之后要做的操作：

    @After("login()")
    public void after(){
        System.out.println("结束了！");
    }
    //获取返回之后的内容：
    @AfterReturning(returning = "object", pointcut ="login()")
    public void doAfterReturning(Object object){
        System.out.println("返回了！");
    }

    */
}
