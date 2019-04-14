package buct.software.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author  高谦
 * 专业数据表 major 数据库操作类
 */
@Repository
public interface MajorDao {
    public List<String> getMajorNamesByCollegeName(String collegeName);
    public Integer getMajorIdByMajorName(String majorName);
}
