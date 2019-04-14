package buct.software.service;

import buct.software.dao.SemesterDao;
import buct.software.domain.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 高谦
 * 学期表服务层
 * <p>
 * 下面的接口请改为从数据库中获取。
 * @implement 孟庆强
 */

@Service
public class SemesterService {
    @Autowired
    SemesterDao semesterDao;

    public Semester getCurrentSemesterInfo() {
        List<Semester> all = semesterDao.getSemesterMostId();
        return all.get(0);
    }

    public Integer getCurrentSemesterId() {
        List<Semester> all = semesterDao.getSemesterMostId();
        return 1;
        //return all.get(0).getSemesterId();
    }

    public List<String> getSemesterList() {
        List<Semester> all = semesterDao.getAll();
        List<String> semesterList = new ArrayList<>();
        for (Semester semester : all) {
            semesterList.add(semester.toString());
        }
        return semesterList;
    }

    public List<Semester> getSemesterDomain() {
        List<Semester> all = semesterDao.getAll();
        return all;
    }
}
