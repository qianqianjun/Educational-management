package buct.software.controller;
import buct.software.domain.User;
import buct.software.service.UserService;
import buct.software.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author  高谦
 * 用户表的相关接口
 */

@RestController
public class UserControllerApi {
    @Autowired
    UserService userService;
    @PostMapping("/changepassword")
    public ResponseMessage changePassword(@RequestParam("newpass") String password,
                                          HttpServletRequest request){
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        Integer account=user.getAccount();

        System.out.println(account);
        System.out.println(password);
        return new ResponseMessage(200,"修改成功",null);
    }
}