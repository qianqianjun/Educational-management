package buct.software.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/coursetable")
    public String coursetable(){
        return "coursetable";
    }
    @GetMapping("/examinfo")
    public String examinfo(){
        return "examinfo";
    }
    @GetMapping("/examtime")
    public String examtime(){
        return "examtime";
    }
    @GetMapping("/gradeinfo")
    public String gradeinfo(){
        return "gradeinfo";
    }
    @GetMapping("/process")
    public String process(){
        return "process";
    }
    @GetMapping("/selecttopic")
    public String selecttopic(){
        return "selecttopic";
    }

    @GetMapping("/mobilelogin")
    public String mobileindex(){
        return "MobileLogin";
    }

    @GetMapping("/mobilestudenthome")
    public String mobilestudenthome(){
        return "msh";
    }
}
