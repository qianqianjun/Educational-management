package buct.software.service;

import buct.software.dao.StudentDao;
import buct.software.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 高谦
 * 学生表相应的服务。
 */
@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    /**
     * 根据学号获得学生信息
     *
     * @param sno
     * @return
     */

    public Student getStudentBySno(Integer sno) {
        return studentDao.getBySno(sno);
    }

    public List<Student> getAllStudent() {
        return studentDao.getAll();
    }

    public List<Student> getStudentsByExample(Student student) {
        return studentDao.getStudentsByExample(student);
    }
    @Transactional
    public void saveStudents(List<Student> students) {
        for (Student student : students) {
            studentDao.insertStudnet(student);
        }
    }

    public void saveStudent(Student student) {
        studentDao.insertStudnet(student);
    }
}
