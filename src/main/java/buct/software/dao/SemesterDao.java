package buct.software.dao;

import buct.software.domain.Semester;

import java.util.List;

/**
 * @author 高谦
 * 学期表 semester 数据库操作类
 */

public interface SemesterDao {
    public void insertSemester(Semester semester);
    public List<Semester> getAll();
    public List<Semester> getSemesterMostId();
}
