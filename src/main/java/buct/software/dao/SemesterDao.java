package buct.software.dao;


import org.springframework.stereotype.Repository;

import buct.software.domain.Semester;

import java.util.List;


/**
 * @author 高谦
 * 学期表 semester 数据库操作类
 * @implements 孟庆强
 */

@Repository
public interface SemesterDao {
    public Integer getSemesterId(String year, String semester);

    public void insertSemester(Semester semester);

    public List<Semester> getAll();

    public List<Semester> getSemesterMostId();
}
