package buct.software.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 高谦
 * 界面模板的加载。
 */
@Controller

/**
 * 请各位组长注意！，下面写的是示例代码，也就是说下面的controller 接口是用来测试和演示使用的
 * 各组长建立本组子系统对应的 controller ，仿照这个写法来进行
 */
public class IndexController {
    //首页
    @RequestMapping("/index")
    public String index(){  // 这个函数名称我们不用care
        // 返回的是一个模板的名称，在templates 下的一个文件名，这里返回的是login.html
        return "login";
    }


    @RequestMapping("/content")
    public String content(){
        return "content";
    }
}
