package buct.software.controller;
import buct.software.service.CollegeService;
import buct.software.service.SelectCourseService;
import buct.software.service.SemesterService;
import buct.software.utils.ResponseMessage;
import buct.software.views.SelectCourseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
/**
 *  @author  高谦
 *  选课子系统所有用到的api 函数。
 */
@RestController
public class SelectCourseControllerApi {
    @Autowired
    CollegeService collegeService;
    @Autowired
    SelectCourseService selectCourseService;
    @Autowired
    SemesterService semesterService;
    /**
     * 根据条件查询课程的接口
     * @param college  开课学院
     * @param capacity  是否有余量
     * @param cno  课程号码
     * @param cname  课程名称
     * @param tname  教师姓名
     * @return
     */
    @PostMapping("/filtercourse")
    public ResponseMessage filterCourse(@RequestParam("college") String college,
                                        @RequestParam("capacity") String capacity,
                                        @RequestParam("cno") String cno,
                                        @RequestParam("cname") String cname,
                                        @RequestParam("tname") String tname){
        ArrayList<SelectCourseView> courseViews=
                (ArrayList<SelectCourseView>) selectCourseService.getCourseList(college,capacity,cno,cname,tname);
        return new ResponseMessage(ResponseMessage.SUCCESS,"请求成功",courseViews);
    }

    /**
     * 返回所有学院的 Controller 接口。
     * @return
     */
    @GetMapping("/getcolleges")
    public ResponseMessage getCollege(){
        return collegeService.getAllCollege();
    }

    /**
     * 根据学院id 返回学院相关信息的接口
     * @param id  学院的id
     * @return  返回的是
     */
    @PostMapping("/getcollegebyid")
    public ResponseMessage getCollegeById(@RequestParam String id){
        return collegeService.getCollegeById(id);
    }

}
