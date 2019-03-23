package buct.software.dao;
import buct.software.domain.College;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 高谦
 * 学院 college 表 数据库操作类。
 */


@Repository
public interface CollegeDao {
    List<College> getColleges();
    College getCollegeByName(String coll);
}
