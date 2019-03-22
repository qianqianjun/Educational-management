package buct.computer.one.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 高谦
 * 用于启动的界面
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
