package buct.software.dao;

import buct.software.domain.Teacher;
import org.springframework.stereotype.Repository;

/**
 * @author 高谦
 * 教师表 teacher 数据库擦作类。
 */

@Repository
public interface TeacherDao {
    Teacher getByTno(Integer tno);
}
