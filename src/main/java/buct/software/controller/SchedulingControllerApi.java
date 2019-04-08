package buct.software.controller;

import buct.software.domain.Scheduling;
import buct.software.service.SchedulingService;
import buct.software.utils.ResponseMessage;
import com.sun.org.apache.xalan.internal.xsltc.runtime.StringValueHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchedulingControllerApi {
    @Autowired
    SchedulingService schedulingService;



    @PostMapping("/getSchedulings")
    public ResponseMessage getSchedulings(@RequestParam("semesterId") String semesterId,
                                          @RequestParam("cno") String cno,
                                          @RequestParam("tno") String tno,
                                          @RequestParam("status") String status,
                                          @RequestParam("capacity") String capacity,
                                          @RequestParam("address") String address,
                                          @RequestParam("percent") String percent,
                                          @RequestParam("majorGrade") String majorGrade,
                                          @RequestParam("courseTime") String CourseTime){
        if(semesterId.compareTo("null") == 0) semesterId="-1";
        if(cno.compareTo("null") == 0) cno=null;
        if(tno.compareTo("null") == 0) tno=null;
        if(status.compareTo("null") == 0) status=null;
        if(capacity.compareTo("null") == 0) capacity="-1";
        if(address.compareTo("null") == 0) address=null;
        if(percent.compareTo("null") == 0) percent=null;
        if(majorGrade.compareTo("null") == 0) majorGrade=null;
        if(CourseTime.compareTo("null") == 0) CourseTime=null;
        Scheduling scheduling = new Scheduling(Integer.valueOf(semesterId),cno,tno,status,
                Integer.valueOf(capacity),address,percent,
                                majorGrade,CourseTime);
        System.out.println(scheduling);
        return schedulingService.getSchedulings(scheduling);

    }
}
