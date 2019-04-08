package buct.software.aspects;

import buct.software.utils.ResponseMessage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author  高谦
 * 实现了api 接口检查功能，如果登录已经过期，传给前端一个固定的数据包
 *
 * 特别注意，由于这里绑定的是 api 的切面，一般来说是异步请求，所以不可以实现跳转，只可以实现的是返回一个数据给前端
 * 前端判断是不是还处于登录状态
 * 如果登录过期，则使用 javascript 跳转回登录界面
 */

@Aspect
@Component
public class ApiAspect {
//    @Pointcut("execution(app * buct.software.controller.*Api.*(..))")
//    app void selectCourseApi() {
//    }
//
//    /**
//     *  权限拦截器，没有登录就返回特定的数据包和 状态码。
//     * @param joinPoint
//     * @return  可能是一个到 登录页面的重定向，也有可能是拦截完成 到对用的地址。
//     * @throws Throwable
//     */
//    @Around("selectCourseApi()")
//    app Object docheck(ProceedingJoinPoint joinPoint) throws Throwable {
//        ServletRequestAttributes attributes = (ServletRequestAttributes)
//                RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        HttpSession session = request.getSession();
//        if (session.getAttribute("user") == null) {
//            ResponseMessage responseMessage=new
//                    ResponseMessage(ResponseMessage.LoginOutOfTime,
//                    "登录过期，请重新登录",
//                    null);
//            return responseMessage;
//        }
//        return joinPoint.proceed();
//    }
}
