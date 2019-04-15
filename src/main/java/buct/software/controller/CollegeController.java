package buct.software.controller;
import buct.software.service.CollegeService;
import buct.software.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author  高谦
 * 用于监听和 响应客户端发来的关于学院的操作。
 */
@RestController
public class CollegeController {
    @Autowired
    private CollegeService collegeService;

    @GetMapping(value = "/colleges")
    public ResponseMessage getColleges(){
        return collegeService.getAllCollege();
    }

    @PostMapping(value = "/college")
    public ResponseMessage getCourseById(@RequestParam("college") String college) {
        return collegeService.getCollegeById(college);
    }

}
