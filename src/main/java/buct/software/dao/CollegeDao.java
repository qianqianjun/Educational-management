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
<<<<<<< HEAD
    public List<College> getColleges();

    public College getCollegeById(String id);

=======
    List<College> getColleges();
    College getCollegeByName(String coll);
>>>>>>> 60940058f823a8a81e7a43145f2e9d3926273e32
}

