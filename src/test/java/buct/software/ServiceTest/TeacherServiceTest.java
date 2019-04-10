package buct.software.ServiceTest;

import buct.software.domain.Teacher;
import buct.software.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 孟庆强
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherServiceTest {
    @Autowired
    TeacherService teacherService;

    @Test
    public void addTeachers() {
        Teacher t2 = new Teacher();
        t2.setCollegeId(1);
        t2.setEmail("2");
        t2.setOffice("2");
        t2.setRank("22");
        t2.setTno(212);
        t2.setSex("2");
        t2.setPhone("2q1");
        t2.setTname("222");
        Teacher teacher = new Teacher();
        teacher.setCollegeId(1);
        teacher.setEmail("2");
        teacher.setOffice("2");
        teacher.setRank("22");
        teacher.setTno(213);
        teacher.setSex("2");
        teacher.setPhone("2q1");
        teacher.setTname("222");
        List<Teacher> list = new ArrayList<>();
        list.add(t2);
        list.add(teacher);
        teacherService.addTeachers(list);
    }

    @Test
    public void updateTeachers() {
        Teacher t2 = new Teacher();
        t2.setCollegeId(1);
        t2.setEmail("2");
        t2.setOffice("2");
        t2.setRank("22");
        t2.setTno(212);
        t2.setSex("2");
        t2.setPhone("2q1");
        t2.setTname("springxml");
        Teacher teacher = new Teacher();
        teacher.setCollegeId(1);
        teacher.setEmail("2");
        teacher.setOffice("2");
        teacher.setRank("22");
        teacher.setTno(213);
        teacher.setSex("2");
        teacher.setPhone("2q1");
        teacher.setTname("springss");
        List<Teacher> list = new ArrayList<>();
        list.add(t2);
        list.add(teacher);
        teacherService.updateTeachers(list);
    }

    @Test
    public void addTeacher() {
        Teacher t2 = new Teacher();
        t2.setCollegeId(1);
        t2.setEmail("2");
        t2.setOffice("2");
        t2.setRank("22");
        t2.setTno(214);
        t2.setSex("2");
        t2.setPhone("2q1");
        t2.setTname("222");
        teacherService.addTeacher(t2);
    }

    @Test
    public void updateTeacherById() {
        Teacher t2 = new Teacher();
        t2.setCollegeId(1);
        t2.setEmail("2");
        t2.setOffice("2");
        t2.setRank("22");
        t2.setTno(214);
        t2.setSex("2");
        t2.setPhone("sss");
        t2.setTname("222");
        teacherService.updateTeacherById(214, t2);
    }
}
