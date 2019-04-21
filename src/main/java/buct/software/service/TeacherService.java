package buct.software.service;

import buct.software.dao.TeacherDao;
import buct.software.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 孟庆强
 */
@Service
public class TeacherService {
    @Autowired
    TeacherDao teacherDao;

    public Teacher getTeacherByTno(Integer tno) {
        Teacher teacher = teacherDao.getByTno(tno);
        return teacher;
    }

    @Transactional
    public void addTeachers(List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            teacherDao.insertTeacher(teacher);
        }
    }

    @Transactional
    public void updateTeachers(List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            teacherDao.updateTeacher(teacher);
        }
    }

    public void addTeacher(Teacher teachers) {
        teacherDao.insertTeacher(teachers);
    }

    public void updateTeacherById(Integer id, Teacher teacher) {
        teacher.setTno(id);
        teacherDao.updateTeacher(teacher);
    }

    public List<Teacher> getAllTeacher() {
        List<Teacher> teachers = teacherDao.queryByExample(new Teacher());
        return teachers;
    }
    public List<Teacher> getTeacherByExample(Teacher teacher){
        return teacherDao.queryByExample(teacher);
    }
}
