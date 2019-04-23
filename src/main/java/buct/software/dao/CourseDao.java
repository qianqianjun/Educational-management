package buct.software.dao;

import buct.software.domain.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 高谦
 * course表 数据库操作类。
 */

@Repository
public interface CourseDao {
    public List<Course> getAll();
    public Course getByCno(String cno);
    public Boolean addCourse(Integer cno,String cname,String college,String description,String status);
    public Boolean checkCourse(Integer cno);

}
