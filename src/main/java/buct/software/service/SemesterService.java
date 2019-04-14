package buct.software.service;

import buct.software.domain.Semester;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  高谦
 * 学期表服务层
 *
 * 下面的接口请改为从数据库中获取。
 */

@Service
public class SemesterService {
    public Semester getCurrentSemesterInfo(){
        Semester semester=new Semester();
        semester.setSemesterId(1);
        semester.setSemester("一");
        semester.setStart("2016");
        semester.setEnd("2017");
        return semester;
    }
    public Integer getCurrentSemesterId(){
        return 1;
    }

    public List<String> getSemesterList(){
        Semester semester=new Semester();
        semester.setSemesterId(1);
        semester.setSemester("一");
        semester.setStart("2016");
        semester.setEnd("2017");
        ArrayList<String> semesters=new ArrayList<>();
        semesters.add(semester.toString());
        return semesters;
    }
}
