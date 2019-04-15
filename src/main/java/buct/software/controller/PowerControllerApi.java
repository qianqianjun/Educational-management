package buct.software.controller;

import buct.software.domain.User;
import buct.software.service.PowerService;
import buct.software.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 孟庆强
 */
@RestController
public class PowerControllerApi {
    @Autowired
    private PowerService powerService;

    /**
     * 用于调试所有权限信息
     *
     * @return Power
     */
    @GetMapping("/getPowerStatus")
    public ResponseMessage getPowerStatus() {
        return new ResponseMessage(ResponseMessage.SUCCESS, "Status", powerService.getStatus());
    }

    /**
     * 获取学籍异动权限
     *
     * @return Boolean
     */
    @GetMapping("/getPowerAbnormal")
    public ResponseMessage getPowerAbnormal() {
        return new ResponseMessage(ResponseMessage.SUCCESS, "Abnormal", powerService.getAbnormal());
    }

    /**
     * 获取成绩录入权限
     *
     * @return Boolean
     */
    @GetMapping("/getPowerScore")
    public ResponseMessage getPowerScore() {
        return new ResponseMessage(ResponseMessage.SUCCESS, "Score", powerService.getScore());
    }

    /**
     * 获取选课权限
     *
     * @return Boolean
     */
    @GetMapping("/getPowerSelectCourse")
    public ResponseMessage getPowerSelectCourse() {
        return new ResponseMessage(ResponseMessage.SUCCESS, "SelectCourse", powerService.getSelectCourse());
    }

    /**
     * 打开选课权限
     *
     * @return Boolean
     */
    @RequestMapping("/openPowerSelectCourse")
    public ResponseMessage setPowerSelectCourse(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.getType() == 2) {
            powerService.openSelectCourse();
            return new ResponseMessage(ResponseMessage.SUCCESS, "OpenSelectCourse", true);
        }
        return new ResponseMessage(ResponseMessage.WRONG, "OpenSelectCourse", false);
    }

    /**
     * 关闭选课权限
     *
     * @return Boolean
     */
    @RequestMapping("/closePowerSelectCourse")
    public ResponseMessage closePowerSelectCourse(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.getType() == 2) {
            powerService.closeSelectCourse();
            return new ResponseMessage(ResponseMessage.SUCCESS, "CLoseSelectCourse", true);
        }
        return new ResponseMessage(ResponseMessage.WRONG, "CLoseSelectCourse", false);
    }

    /**
     * 打开学籍异动权限
     *
     * @return Boolean
     */
    @RequestMapping("/closePowerAbnormal")
    public ResponseMessage closePowerAbnormal(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.getType() == 2) {
            powerService.closeAbnormal();
            return new ResponseMessage(ResponseMessage.SUCCESS, "CloseAbnormal", true);
        }
        return new ResponseMessage(ResponseMessage.WRONG, "CloseAbnormal", false);
    }

    /**
     * 关闭学籍异动权限
     *
     * @return Boolean
     */
    @RequestMapping("/openPowerAbnormal")
    public ResponseMessage openPowerAbnormal(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.getType() == 2) {
            powerService.openAbnormal();
            return new ResponseMessage(ResponseMessage.SUCCESS, "OpenAbnormal", true);
        }
        return new ResponseMessage(ResponseMessage.WRONG, "OpenAbnormal", false);
    }

    /**
     * 打开成绩录入权限
     *
     * @return Boolean
     */
    @RequestMapping("/openPowerScore")
    public ResponseMessage openPowerScore(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.getType() == 2) {
            powerService.openScore();
            return new ResponseMessage(ResponseMessage.SUCCESS, "OpenScore", true);
        }
        return new ResponseMessage(ResponseMessage.WRONG, "OpenScore", false);
    }

    /**
     * 打开关闭录入权限
     *
     * @return Boolean
     */
    @RequestMapping("/closePowerScore")
    public ResponseMessage closePowerScore(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.getType() == 2) {
            powerService.closeScore();
            return new ResponseMessage(ResponseMessage.SUCCESS, "CloseScore", true);
        }
        return new ResponseMessage(ResponseMessage.WRONG, "CloseScore", false);
    }
}
