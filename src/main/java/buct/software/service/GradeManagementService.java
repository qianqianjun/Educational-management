package buct.software.service;

import buct.software.dao.GradeManagementDao;
import buct.software.dao.SchedulingDao;
import buct.software.domain.Course;
import buct.software.domain.Scheduling;
import buct.software.views.GradeManagementView;
import buct.software.domain.SelectCourse;
import buct.software.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @author  王艺琳
 * 成绩管理子系统服务层
 */
@Service
public class GradeManagementService {
    @Autowired
    GradeManagementDao gradeManagementDao;
    SchedulingDao schedulingDao;
    ExcelUtil excelUtil;

    /*学生端*/
    /**
     * 根据学期号获取该学生的课程成绩信息。
     * @param sno 学号
     * @return
     */
    public List<GradeManagementView> getCourseGrade(String start,String semester,Integer sno){
        GradeManagementView parma = new GradeManagementView();
        parma.setSno(sno);
        parma.setStart(start); //为空则默认选择全部学年
        parma.setSemester(semester);//为-1则默认选择该学年全部学期
        return gradeManagementDao.getCourseGrade(parma);
    }


    /*教师端*/
    /**
     * 根据学期+课程+学号获得某个学生的课程成绩信息。学号为空时返回选这个课所有学生的成绩
     * @param sno 学号
     * @param cno 课程号
     * @return
     */
    public List<GradeManagementView> getStudentsGrade(String start,String semester,Integer cno,Integer tno, Integer sno)
    {
        GradeManagementView parma = new GradeManagementView();
        parma.setCno(cno);
        parma.setTno(tno);
        parma.setSno(sno);
        parma.setStart(start);
        parma.setSemester(semester);
        return gradeManagementDao.getStudentsGrade(parma);
    }
    /**
     * 获取课程百分比
     */
    public String getCoursePercent(Integer semesterId,Integer cno){
        GradeManagementView parma = new GradeManagementView();
        parma.setCno(cno);
        parma.setSemesterId(semesterId);
        return gradeManagementDao.getCoursePercent(parma);
    }

    /**
     * 更改某个学生的课程成绩信息。
     * @param sno 学号
     * @param cno 课程号
     * @return
     */
    public Integer setStudentGradeById(Integer semesterId,Integer cno,Integer sno,String detail,Integer totalScore){
        SelectCourse parma = new SelectCourse();
        parma.setCno(cno);
        parma.setSno(sno);
        parma.setSemesterId(semesterId);
        parma.setDetail(detail);
        parma.setTotalScore(totalScore);

        return gradeManagementDao.setStudentGradeById(parma);
    }



    /**
     * excel导入数据库
     */

    public Boolean setStudentGradeInGroup(Integer semesterId ,Integer cno, File file) {
        System.out.println("导入数据开始。。。。。。");
        GradeManagementView p = new GradeManagementView();
        p.setSemesterId(semesterId);
        p.setCno(cno);
        //tmpend
        String percent = gradeManagementDao.getCoursePercent(p);
        String[] tmp = percent.split(",");
        int p1 = Integer.parseInt(tmp[0]);
        int p2 = Integer.parseInt(tmp[1]);
        int p3 = Integer.parseInt(tmp[2]);
        System.out.print("ppp"+p1);
        System.out.print(p2);
        System.out.println(p3);
        try {
            List<Object[]> list = excelUtil.importExcel(file);
            for (int i = 0; i < list.size(); i++) {
                SelectCourse parma = new SelectCourse();
                String[] detail = ((String) list.get(i)[1]).split(",");
                int g1 = Integer.parseInt(detail[0]);
                int g2 = Integer.parseInt(detail[1]);
                int g3 = Integer.parseInt(detail[2]);
                System.out.print("grade"+g1);
                System.out.print(g2);
                System.out.println(g3);
                int ts = (p1*g1+p2*g2+p3*g3)/100;
                System.out.println("ts"+ts);
                parma.setCno(cno);
                parma.setSemesterId(semesterId);
                parma.setSno((Integer) list.get(i)[0]);
                parma.setDetail((String) list.get(i)[1]);//先规定三栏成绩写在一格里，逗号分隔
                parma.setTotalScore(ts);
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

    /**
     * 获取老师学期学年教的所有课的课程号和课程名
     */
    public List<Course> getCourseOfTeacher(String start,String semester,Integer tno)
    {
        GradeManagementView parma = new GradeManagementView();
        parma.setSemester(semester);
        parma.setStart(start);
        parma.setTno(tno);
        return gradeManagementDao.getCourseOfTeacher(parma);
    }


}
