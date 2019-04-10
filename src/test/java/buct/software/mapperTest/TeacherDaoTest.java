package buct.software.mapperTest;

import buct.software.dao.TeacherDao;
import buct.software.domain.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherDaoTest {
    @Autowired
    TeacherDao teacherDao;
    @Test
    public void insertTeacher(){
        Teacher teacher = new Teacher();
        teacher.setCollegeId(1);
        teacher.setEmail("2");
        teacher.setOffice("2");
        teacher.setRank("22");
        teacher.setTno(21);
        teacher.setSex("2");
        teacher.setPhone("2q1");
        teacher.setTname("222");
        teacherDao.insertTeacher(teacher);
    }
    @Test
    public void queryByExample(){
        Teacher teacher = new Teacher();
        teacher.setEmail("2");
        teacher.setOffice("2");
        teacher.setRank("22");
        teacher.setSex("2");
        teacher.setPhone("2q1");
        teacher.setTname("222");
        List<Teacher> teachers = teacherDao.queryByExample(teacher);
        System.out.println(teachers);
    }
}
