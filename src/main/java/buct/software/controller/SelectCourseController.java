package buct.software.controller;

import buct.software.service.CollegeService;
import buct.software.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author  高谦
 * 学生选课子系统 controller
 */
@Controller
public class SelectCourseController {
    @Autowired
    CollegeService collegeService;

    /**
     * 返回所有学员的接口。
     * @return
     */
    @ResponseBody
    @GetMapping("/getcolleges")
    public ResponseMessage getCollege(){
        return collegeService.getAllCollege();
    }

    @ResponseBody
    @PostMapping("/getcollegebyid")
    public ResponseMessage getCollegeById(@RequestParam String id){
        return collegeService.getCollegeById(id);
    }

    @GetMapping("/selectcourse")
    public String selectCourse(){
        return "selectcourse";
    }
}
