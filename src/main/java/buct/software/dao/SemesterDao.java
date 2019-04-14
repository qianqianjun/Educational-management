package buct.software.dao;

import org.springframework.stereotype.Repository;

/**
 * @author 高谦
 * 学期表 semester 数据库操作类
 */
@Repository
public interface SemesterDao {
    public Integer getSemesterId(String year,String semester);
}
