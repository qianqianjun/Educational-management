package buct.software.service;

import buct.software.dao.GradeManagementDao;
import buct.software.domain.SelectCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author  王艺琳
 * 成绩管理子系统服务层
 */
@Service
public class GradeManagementService {
    @Autowired
    GradeManagementDao gradeManagementDao;
    /*学生端*/
    /**
     * 根据学期号获取该学生的课程成绩信息。
     * @param sno 学号
     * @param semesterId  学期id
     * @return
     */
    public List<SelectCourse> getCourseGrade(Integer semesterId,Integer sno){
        SelectCourse parma = new SelectCourse();
        parma.setSno(sno);
        parma.setSemesterId(semesterId);
        return gradeManagementDao.getCourseGrade(parma);
    }
    /*教师端*/
    /**
     * 根据学期+课程+学号获得某个学生的课程成绩信息。
     * @param sno 学号
     * @param cno 课程号
     * @param semesterId  学期id
     * @return
     */
    public List<SelectCourse> getStudentGrade(Integer semesterId,Integer cno,Integer sno){
        SelectCourse parma = new SelectCourse();
        parma.setCno(cno);
        parma.setSno(sno);
        parma.setSemesterId(semesterId);
        return gradeManagementDao.getStudentGrade(parma);
    }

    /**
     * 更改某个学生的课程成绩信息。
     * @param sno 学号
     * @param cno 课程号
     * @param semesterId  学期id
     * @return
     */
    public Integer setStudentGradeById(Integer semesterId,Integer cno,Integer sno,String detail){
        SelectCourse parma = new SelectCourse();
        parma.setCno(cno);
        parma.setSno(sno);
        parma.setSemesterId(semesterId);
        parma.setDetail(detail);
        return gradeManagementDao.setStudentGradeById(parma);
    }



}
