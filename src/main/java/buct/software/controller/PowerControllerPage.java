package buct.software.controller;

import buct.software.domain.Power;
import buct.software.domain.User;
import buct.software.service.PowerService;
import buct.software.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class PowerControllerPage {
    @Autowired
    PowerService powerService;
    @Autowired
    SemesterService semesterService;

    public boolean checkPower(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) return false;
        return user.getType() == 2;
    }

    @RequestMapping("/GoHomePage")
    public String homePage(Map<String, Object> parMap, HttpServletRequest request) {
        Power status = powerService.getStatus();
        parMap.put("power", status);
        request.setAttribute("currentSemesterInfo",semesterService.getCurrentSemesterInfo());
        return "HomePage";
    }

    //    @CachePut(value = "parMap", key = "#parMap")
    @RequestMapping("/PowerManage")
    public String powerControll(Map<String, Object> parMap, HttpServletRequest request) {
        if (checkPower(request) == false) return "error";
        request.setAttribute("score", powerService.getScore());
        request.setAttribute("abnormal", powerService.getAbnormal());
        request.setAttribute("selectCourse", powerService.getSelectCourse());
        return "PowerManage";
    }

    @PostMapping("/OpenSelectCourse")
    public String openSelectCourse(Map<String, Object> parMap, HttpServletRequest request) {
        if (checkPower(request) == false) return "error";
        powerService.openSelectCourse();
        return "redirect:/PowerManage";
    }

    @PostMapping("/OpenScore")
    public String openScore(Map<String, Object> parMap, HttpServletRequest request) {
        if (checkPower(request) == false) return "error";
        powerService.openScore();
        return "redirect:/PowerManage";
    }

    @PostMapping("/OpenAbnormal")
    public String openAbnormal(Map<String, Object> parMap, HttpServletRequest request) {
        if (checkPower(request) == false) return "error";
        powerService.openAbnormal();
        return "redirect:/PowerManage";
    }

    @PostMapping("/CloseAbnormal")
    public String closeAbnormal(Map<String, Object> parMap, HttpServletRequest request) {
        if (checkPower(request) == false) return "error";
        powerService.closeAbnormal();
        return "redirect:/PowerManage";
    }

    @PostMapping("/CloseScore")
    public String closeScore(Map<String, Object> parMap, HttpServletRequest request) {
        if (checkPower(request) == false) return "error";
        powerService.closeScore();
        return "redirect:/PowerManage";
    }

    @PostMapping("/CloseSelectCourse")
    public String closeSelectCourse(Map<String, Object> parMap, HttpServletRequest request) {
        if (checkPower(request) == false) return "error";
        powerService.closeSelectCourse();
        return "redirect:/PowerManage";
    }
//    @RequestMapping("/HomePage")
}
