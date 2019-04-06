package buct.software.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author  高谦
 * 实现了api 接口检查功能，如果登录已经过期，传给前端一个固定的数据包
 * 使用前端进行判断，如果登录过期，则直接跳转回登录界面
 */

@Aspect
@Component
public class ApiAspect {
}
