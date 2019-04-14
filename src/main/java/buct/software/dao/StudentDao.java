package buct.software.dao;

import buct.software.domain.Student;
import org.springframework.stereotype.Repository;

/**
 * @author 高谦
 * 学生 student 表 数据库操作类
 */

@Repository
public interface StudentDao {
    Student getBySno(Integer sno);
}
