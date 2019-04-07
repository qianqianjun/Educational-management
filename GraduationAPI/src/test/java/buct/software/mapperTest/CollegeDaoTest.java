package buct.software.mapperTest;

import buct.software.dao.CollegeDao;
import buct.software.domain.College;
import buct.software.service.CollegeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollegeDaoTest {
    @Autowired
    CollegeDao collegeDao;
    @Autowired
    CollegeService collegeService;
    @Test
    public void getALL(){
        List<College> colleges = collegeDao.getColleges();
        System.out.println(colleges);
    }
    @Test
    public void getCollegeByName(){

//        College college = collegeDao.getCollegeByName("化学工程学院");
        College college = collegeDao.getCollegeByName("sd");
        System.out.println(college);
    }
}
