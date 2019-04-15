package buct.software.service;

import buct.software.dao.GradeManagementDao;
import buct.software.domain.SelectCourse;
import buct.software.utils.ExcelUtil;
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
    ExcelUtil excelUtil;
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


    /**
     * excel导入数据库
     */
    public Boolean setStudentGradeInGroup(Integer semesterId,Integer cno,String fileName) {
        System.out.println("导入数据开始。。。。。。");
        try {
            List<Object[]> list = excelUtil.importExcel(fileName);
            for (int i = 0; i < list.size(); i++) {
                SelectCourse parma = new SelectCourse();
                parma.setCno(cno);
                parma.setSemesterId(semesterId);
                parma.setSno((Integer) list.get(i)[0]);
                parma.setDetail((String) list.get(i)[1]);//先规定三栏成绩写在一格里，逗号分隔
                gradeManagementDao.setStudentGradeById(parma); //执行insert
            }
            System.out.println("导入数据结束。。。。。。");
            return true;
        } catch (Exception e) {
            System.out.println("导入数据失败。。。。。。");
            e.printStackTrace();
        }
        return false;
    }
}
