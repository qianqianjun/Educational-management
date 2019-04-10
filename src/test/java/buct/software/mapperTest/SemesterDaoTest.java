package buct.software.mapperTest;

import buct.software.dao.SemesterDao;
import buct.software.domain.Semester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SemesterDaoTest {
    @Autowired
    SemesterDao semesterDao;
//    @Autowired
    Semester semester;
    @Test
    public void insertSemester() {
        semester = new Semester();
        semester.setEnd("222");
        semester.setStart("222");
        semester.setSemesterId(1);
        semester.setSemester("222");
//
        semesterDao.insertSemester(semester);
//
    }
    @Test
    public void getSemester(){
        List<Semester> all = semesterDao.getAll();
        System.out.println(all);

    }
    @Test
    public void getSemesterMostId(){
        List<Semester> currentSemester = semesterDao.getSemesterMostId();
        System.out.println(currentSemester);
    }
}
