package buct.software.controller;

import buct.software.domain.Semester;
import buct.software.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;

@Controller
public class SemesterControllerPage {
    @Autowired
    SemesterService semesterService;

    @RequestMapping("/GoSemesterManage")
    public String semesterManage(Map<String, Object> parMap, HttpServletRequest request) {
        Semester currentSemesterInfo = semesterService.getCurrentSemesterInfo();
        request.setAttribute("currentSemesterInfo", currentSemesterInfo);
//        parMap.put("currentSemesterInfo", currentSemesterInfo);
        return "SemesterManage";
    }

    @PostMapping("/CreateNewSemester")
    public String createNewSemester(@RequestParam("semesterid") Integer semesterId,
                                    @RequestParam("semester") String semester,
                                    @RequestParam("start") Date start,
                                    @RequestParam("end") Date end) {
        System.out.println(semesterId);
        System.out.println(semester);
        System.out.println(start);
        System.out.println(end);
        if (semesterId.equals("") && semesterId == null) {
            return "error";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        int startYear = calendar.get(Calendar.YEAR);
        calendar.setTime(end);
        int endYear = calendar.get(Calendar.YEAR);
        semesterService.addSemester(new Semester(semesterId, new Integer(startYear).toString(), new Integer(endYear).toString(), semester));
        return "forward:/GoSemesterManage";
    }

}
