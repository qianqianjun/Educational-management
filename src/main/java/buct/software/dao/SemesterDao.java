package buct.software.dao;


import org.springframework.stereotype.Repository;

import buct.software.domain.Semester;
import org.springframework.stereotype.Repository;

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

    /**
     * @author 高谦
     * 查询特定的学期
     * @param parm  查询条件数据集合
     * @return
     */
    Semester getSemesterByStartAndSemester(Semester parm);

    /**
     * @author 王艺琳
     * @param semesterId  学期的id
     * @return
     */
    Semester getSemesterById(Integer semesterId);
}
