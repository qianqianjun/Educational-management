package buct.software.dao;

import buct.software.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 高谦
 * 学生 student 表 数据库操作类
 * @implements 孟庆强
 */

@Repository
public interface StudentDao {
    public Student getBySno(Integer sno);
    public List<Student> getAll();
    public void insertStudnet(Student student);
    public void updateStudent(Student student);
    List<Student> getStudentsByExample(Student student);
}
