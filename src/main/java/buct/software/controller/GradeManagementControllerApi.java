package buct.software.controller;
import buct.software.domain.Course;
import buct.software.domain.SelectCourse;
import buct.software.domain.Semester;
import buct.software.domain.User;
import buct.software.service.CollegeService;
import buct.software.service.GradeManagementService;
import buct.software.service.SelectCourseService;
import buct.software.service.SemesterService;
import buct.software.service.PowerService;
import buct.software.service.CourseService;
import buct.software.utils.ResponseMessage;
import buct.software.views.GradeManagementView;
import org.safehaus.uuid.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    PowerService powerService;
    @Autowired
    CourseService courseService;

    //传参...
    private Integer tmpcno;
    private Integer tmpSemesterId;


    /* 学生端*/
    /**
     * 学生某学期的成绩信息。
     * @return  这个学期的课表
     */
    @RequestMapping(value ="/getcoursegrade",method = RequestMethod.GET)
    public ResponseMessage getCourseGrade(@RequestParam("semester") String semester,
                                          @RequestParam("start") String start,
                                          HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer semesterId=semesterService.getCurrentSemesterId();
        User user=(User) session.getAttribute("user");
        Integer sno = user.getAccount();
        System.out.println("start"+start);
        System.out.println("semester"+semester);
        System.out.println(sno);
        ArrayList<GradeManagementView> courseGradeTable=(ArrayList<GradeManagementView>)
                gradeManagementService.getCourseGrade(start,semester,sno);
        System.out.println("result"+courseGradeTable);

        return new ResponseMessage(ResponseMessage.SUCCESS,"查询成功！",courseGradeTable);

    }



    /* 教师端*/
    /**
     * @return  学生的成绩信息
     */
    @RequestMapping(value ="/getstudentgrade",method = RequestMethod.GET)
    public ResponseMessage getStudentGrade(@RequestParam("semester") String semester,
                                           @RequestParam("start") String start,
                                           @RequestParam("cno") Integer cno,
                                           @RequestParam("sno") Integer sno,
                                           HttpServletRequest request){
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        Integer tno = user.getAccount();
        System.out.println("cno:"+cno);
        System.out.println("sno:"+sno);

        ArrayList<GradeManagementView> studentGradeTable=(ArrayList<GradeManagementView>)
                    gradeManagementService.getStudentsGrade(start,semester,cno,tno,sno);
        System.out.println("查询到"+studentGradeTable.size());

        System.out.println(tno);
        System.out.println(studentGradeTable);
        return new ResponseMessage(ResponseMessage.SUCCESS,"查询成功！",studentGradeTable);
    }


    /**
     * 老师更改某学期某课程某学生的成绩
     * @return  该学生的成绩信息
     */
    @RequestMapping("/setstudentgradeById")
    public ResponseMessage setStudentGrade(@RequestParam("semester") String semester,
                                           @RequestParam("start") String start,
                                           @RequestParam("cno") String ccno,
                                           @RequestParam("sno") Integer sno,
                                           @RequestParam("detail") String detail,
                                           HttpServletRequest request){
        Integer cno = Integer.valueOf(ccno);
        Semester s=semesterService.getSemesterByStartAndSemester(Integer.valueOf(start),Integer.valueOf(semester));
        Integer semesterId = s.getSemesterId();
        String percent = gradeManagementService.getCoursePercent(semesterId,cno);
        String[] tmp = percent.split(",");
        int p1 = Integer.parseInt(tmp[0]);
        int p2 = Integer.parseInt(tmp[1]);
        int p3 = Integer.parseInt(tmp[2]);
        String[] d = detail.split(",");
        int g1 = Integer.parseInt(d[0]);
        int g2 = Integer.parseInt(d[1]);
        int g3 = Integer.parseInt(d[2]);
        Integer totalScore = (p1*g1+p2*g2+p3*g3)/100;

        System.out.println("change"+detail+" "+totalScore);
        boolean power = powerService.getSelectCourse();
        if(power == false){
            return  new ResponseMessage(ResponseMessage.SUCCESS,"当前时段没有权限访问！",power);
        }
        else{
            gradeManagementService.setStudentGradeById(semesterId,cno,sno,detail,totalScore);
            return new ResponseMessage(ResponseMessage.SUCCESS,"修改成功！",detail);
        }


    }

    /**
     * 获取导入框返回的学年学期课程号
     * @return
     */

    @RequestMapping(value ="/getparams",method = RequestMethod.GET)
    @ResponseBody
    public String getParams(@RequestParam("semester") String semester,
                            @RequestParam("start") String start,
                            @RequestParam("cno") String ccno)
    {
        Semester s=semesterService.getSemesterByStartAndSemester(Integer.valueOf(start),Integer.valueOf(semester));
        Integer semesterId = s.getSemesterId();
        Integer cno = Integer.valueOf(ccno);
        tmpSemesterId = semesterId; //暂存
        tmpcno = cno;
        return "ok";
    }

    /**
     * 方法名：importExcel
     * 功能：上传临时文件并导入，导入完毕后删除临时文件
     * 描述：文件格式为xls或xlsx
     * 创建人：wyl
     */

    @RequestMapping("/import")
    @ResponseBody
    public String importExcel(@RequestParam("file") MultipartFile multfile ) throws IOException {

        // 获取文件名
        String fileName = multfile.getOriginalFilename();
        // 获取文件后缀
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        // 用uuid作为文件名，防止生成的临时文件重复
        final File file = File.createTempFile(getUUID(), prefix);
        // MultipartFile to File
        multfile.transferTo(file);

        /* 测试数据*/
        tmpSemesterId = 1;
        tmpcno = 1;

        if (fileName == null && "".equals(fileName)) {
            return "文件名不能为空！";
        } else {
            if (fileName.endsWith("xls") || fileName.endsWith("xlsx")) {
                Boolean isOk = gradeManagementService.setStudentGradeInGroup(tmpSemesterId,tmpcno,file);

                //导入结束时，删除临时文件
                deleteFile(file);
                if (isOk) {
                    return "导入成功！";
                } else {
                    return "导入失败！";
                }
            }
            return "文件格式错误！";
        }

    }

    /**
     * 获取32位UUID字符串 临时文件名
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 删除临时文件
     *
     * @param files
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @RequestMapping("/getteachercourse")
    @ResponseBody
    private ResponseMessage test(@RequestParam("semester") String semester,
                      @RequestParam("start") String start,
                      HttpServletRequest request){
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        Integer tno = user.getAccount();
        List<Course> teachercourse = gradeManagementService.getCourseOfTeacher(start,semester,tno);
        System.out.println("课程列表"+teachercourse);
        return new ResponseMessage(ResponseMessage.SUCCESS,"查询成功！",teachercourse);
    }




}
