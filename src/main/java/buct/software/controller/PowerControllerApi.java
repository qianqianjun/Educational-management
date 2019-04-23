package buct.software.controller;

import buct.software.domain.User;
import buct.software.service.PowerService;
import buct.software.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author 孟庆强
 * 权限控制的controller
 */

@RestController
public class PowerControllerApi {
    @Autowired
    private PowerService powerService;

    @GetMapping("/getPowerStatus")
    public ResponseMessage getPowerStatus() {
        return new ResponseMessage(ResponseMessage.SUCCESS, "Status", powerService.getStatus());
    }

    @GetMapping("/getPowerAbnormal")
    public ResponseMessage getPowerAbnormal() {
        return new ResponseMessage(ResponseMessage.SUCCESS, "Abnormal", powerService.getAbnormal());
    }

    @GetMapping("/getPowerScore")
    public ResponseMessage getPowerScore() {
        return new ResponseMessage(ResponseMessage.SUCCESS, "Score", powerService.getScore());
    }

    @GetMapping("/getPowerSelectCourse")
    public ResponseMessage getPowerSelectCourse() {
        return new ResponseMessage(ResponseMessage.SUCCESS, "SelectCourse", powerService.getSelectCourse());
    }

    @PostMapping("/openPowerSelectCourse")
    public ResponseMessage setPowerSelectCourse(HttpServletRequest request, HttpServletResponse response ) {
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.getType() == 2) {
            powerService.openSelectCourse();
            return new ResponseMessage(ResponseMessage.SUCCESS, "OpenSelectCourse", "SUCCESS");
        }
        return new ResponseMessage(ResponseMessage.WRONG, "OpenSelectCourse", "FALSE");
    }
}
