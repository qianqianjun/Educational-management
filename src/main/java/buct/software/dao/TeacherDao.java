package buct.software.dao;

import buct.software.domain.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 高谦
 * 教师表 teacher 数据库擦作类。
 * @implements 孟庆强
 */

@Repository
public interface TeacherDao {
    public Teacher getByTno(Integer tno);

    //    public List<Teacher> getAll();
    public void insertTeacher(Teacher teacher);

    public List<Teacher> queryByExample(Teacher example);

    public void updateTeacher(Teacher teacher);
}
