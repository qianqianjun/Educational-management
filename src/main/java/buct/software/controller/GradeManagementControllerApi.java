package buct.software.controller;
import buct.software.domain.SelectCourse;
import buct.software.domain.User;
import buct.software.service.CollegeService;
import buct.software.service.GradeManagementService;
import buct.software.service.SelectCourseService;
import buct.software.service.SemesterService;
import buct.software.utils.ResponseMessage;
import buct.software.views.SelectCourseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *  @author  王艺琳
 *  成绩管理子系统所有用到的api 函数。
 */
@RestController
public class GradeManagementControllerApi {
    @Autowired
    GradeManagementService gradeManagementService;
    @Autowired
    CollegeService collegeService;
    @Autowired
    SelectCourseService selectCourseService;
    @Autowired
    SemesterService semesterService;

    /* 学生端*/
    /**
     * 学生某学期的成绩信息。
     * @param semesterId   学期id
     * @param sno 学号
     * @return  这个学期的课表
     */
    @RequestMapping("/getcoursegrade")
    public ResponseMessage getCourseGrade( HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer semesterId=semesterService.getCurrentSemesterId();
        User user=(User) session.getAttribute("user");
        Integer sno = user.getAccount();
        ArrayList<SelectCourse> courseGradeTable=(ArrayList<SelectCourse>)
                gradeManagementService.getCourseGrade(semesterId,sno);
        System.out.println("查询到"+courseGradeTable.size());
        return new ResponseMessage(ResponseMessage.SUCCESS,"查询成功！",courseGradeTable);

    }
    /* 教师端*/
    /**
     * 老师查询当前学期某课程某学生的成绩
     * @param sno 学号
     * @param cno 课程号
     * @return  该学生的成绩信息
     */
    @RequestMapping("/getstudentgrade")
    public ResponseMessage getStudentGrade(@RequestParam("cno") Integer cno,
                                           @RequestParam("sno") Integer sno){
        Integer semesterId=semesterService.getCurrentSemesterId();
        ArrayList<SelectCourse> studentGradeTable=(ArrayList<SelectCourse>)
                gradeManagementService.getStudentGrade(semesterId,cno,sno);
        System.out.println("查询到"+studentGradeTable.size());
        return new ResponseMessage(ResponseMessage.SUCCESS,"查询成功！",studentGradeTable);

    }

    /**
     * 老师更改某学期某课程某学生的成绩
     * @return  该学生的成绩信息
     */
    @RequestMapping("/setstudentgradeById")
    public ResponseMessage setStudentGrade(@RequestParam("cno") Integer cno,
                                           @RequestParam("sno") Integer sno,
                                           @RequestParam("detail") String detail){
        Integer semesterId=semesterService.getCurrentSemesterId();
        gradeManagementService.setStudentGradeById(semesterId,cno,sno,detail);
        return new ResponseMessage(ResponseMessage.SUCCESS,"修改成功！",detail);

    }

    /**
     * 方法名：import
     * 功能：导入
     * 描述：文件格式为xls或xlsx
     * 创建人：typ
     * 创建时间：2018/10/19 10:06
     * 修改人：
     * 修改描述：
     * 修改时间：
     */
    @GetMapping("/import")
    public String importExcel(Integer semesterId,Integer cno,String fileName) {
//        fileName = "E:\\Educational-management\\test.xlsx";
//        semesterId = 1;
//        cno =1;
        if (fileName == null && "".equals(fileName)) {
            return "文件名不能为空！";
        } else {
            if (fileName.endsWith("xls") || fileName.endsWith("xlsx")) {
                Boolean isOk = gradeManagementService.setStudentGradeInGroup(semesterId,cno,fileName);
                if (isOk) {
                    return "导入成功！";
                } else {
                    return "导入失败！";
                }
            }
            return "文件格式错误！";
        }
    }

}
