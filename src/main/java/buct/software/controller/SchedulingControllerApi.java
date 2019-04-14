package buct.software.controller;

import buct.software.domain.Scheduling;
import buct.software.service.CollegeService;
import buct.software.service.MajorService;
import buct.software.service.PlanningService;
import buct.software.service.SchedulingService;
import buct.software.utils.ResponseMessage;
import com.sun.org.apache.xalan.internal.xsltc.runtime.StringValueHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchedulingControllerApi {
    @Autowired
    SchedulingService schedulingService;
    @Autowired
    CollegeService collegeService;
    @Autowired
    MajorService majorService;
    @Autowired
    PlanningService planningService;


    @PostMapping("/getCollegeNames")
    public ResponseMessage getCollegeNames(){
        return collegeService.getCollegeNames();
    }

    @PostMapping("/getMajorNamesByCollegeName")
    public ResponseMessage getMajorNamesByCollegeName(@RequestParam("collegeName") String collegeName){
        return majorService.getMajorNamesByCollegeName(collegeName);
    }

    @PostMapping("/getWillCourses")
    public ResponseMessage getWillCourses(@RequestParam("year") String year,
                                          @RequestParam("semester") String semester,
                                          @RequestParam("majorName") String majorName,
                                          @RequestParam("grade") String grade){
        return planningService.getWillCourses(year,semester,majorName,grade);
    }

    @PostMapping("/addWillCourse")
    public ResponseMessage addWillCourse(@RequestParam("cno") String cno,
                                         @RequestParam("cname") String cname,
                                         @RequestParam("college") String college,
                                         @RequestParam("description") String description,
                                         @RequestParam("status") String status,
                                         @RequestParam("year") String year,
                                         @RequestParam("semester") String semester,
                                         @RequestParam("majorName") String Name,
                                         @RequestParam("grade") String grade){
        return planningService.addWillCourse(cno,cname,college,description,status,year,
                semester,Name,grade);
    }

    @PostMapping("/deleteWillCourse")
    public  ResponseMessage deleteWillCourse(@RequestParam("cno") String cno,
                                             @RequestParam("year") String year,
                                             @RequestParam("semester") String semester,
                                             @RequestParam("majorName") String majorName,
                                             @RequestParam("grade") String grade){
        return planningService.deleteWillCourse(cno,year,semester,majorName,grade);
    }

    @PostMapping("/getCourse")
    public ResponseMessage getCourse(@RequestParam("cno") String cno,
                                     @RequestParam("year") String year,
                                     @RequestParam("semester") String semester,
                                     @RequestParam("majorName") String majorName,
                                     @RequestParam("grade") String grade){
        return schedulingService.getCourse(cno,year,semester,majorName,grade);
    }

    @PostMapping("/addCourse")
    public ResponseMessage addCourse(@RequestParam("cno") String cno,
                                     @RequestParam("tno") String tno,
                                     @RequestParam("year") String year,
                                     @RequestParam("semester") String semester,
                                     @RequestParam("majorName") String majorName,
                                     @RequestParam("grade") String grade,
                                     @RequestParam("status") String status,
                                     @RequestParam("capacity") String capacity,
                                     @RequestParam("address") String address,
                                     @RequestParam("time") String time){
        return  schedulingService.addCourse(cno,tno,year,semester,majorName,grade,
                status,capacity,address,time);
    }

    @PostMapping("/deleteCourse")
    public ResponseMessage deleteCourse(@RequestParam("cno") String cno,
                                        @RequestParam("year") String year,
                                        @RequestParam("semester") String semester,
                                        @RequestParam("majorName") String majorName,
                                        @RequestParam("grade") String grade){
        return schedulingService.deleteCourse(cno,year,semester,majorName,grade);
    }
}
