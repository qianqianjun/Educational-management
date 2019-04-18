package buct.software.controller;

import buct.software.domain.*;
import buct.software.service.QuestionService;
import buct.software.service.StudentService;
import buct.software.service.TeacherService;
import buct.software.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhongrui
 * 用来测试接口是否正常工作
 */

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/StuLookThroughQues")
    public String StuLookThroughQues(HttpServletRequest request, Map<String,Object> map){
        //需要返回的列表数据有 题目 难度 出题老师姓名 是否选中
        HttpSession session =  request.getSession();
        Object userInfo = session.getAttribute("user");
        User user = (User) userInfo;
        int sno = user.getAccount();
        Student student = studentService.getStudentBySno(sno);
        int studentMajor = student.getMajorId();
        List<QuestionStudentInquiry> questionStudentInquiry = questionService.getPartQuestionByMajorid(studentMajor);
        map.put("quesInfos",questionStudentInquiry);
//        System.out.println(questionStudentInquiry.get(0));//debug
        return "StuLookThroughQues";
    }

    @RequestMapping("/StuQuesDetails")
    public String StuQuesDetails(HttpServletRequest request,
     Map<String,Object> map,@RequestParam("questionid") int questionid
    ){
        HttpSession session =  request.getSession();
        Object userInfo = session.getAttribute("user");
        User user = (User) userInfo;
        int sno = user.getAccount();
        Question question = questionService.getSingleQuestionByQuestionid(questionid);
        int tno = question.getTno();
        Teacher teacher = teacherService.getTeacherByTno(tno);
        map.put("quesInfo",question);
        map.put("teaInfo",teacher);
        return "StuQuesDetails";
    }



    @GetMapping("getPQBM")
    public ResponseMessage getPartQuestionByMajorid(){
        return questionService.messageGetPartQuestionByMajorid(1);
    }




    @GetMapping("getQBM")
    public ResponseMessage getQuestionByMajorid(){
        return questionService.messageGetQuestionByMajorid(1);
    }




    @GetMapping("getQBT")
    public ResponseMessage getQuestionByTno(){
        return questionService.messageGetQuestionByTno(1);
    }


    @GetMapping("getQBQ")
    public ResponseMessage getQuestionByQuestionid(){
        List<Question> questions = questionService.getQuestionByMajorid(1);
        Question question = questions.get(0);
        return questionService.messageGetQuestionByQuestionid(question.getQuestionid());
    }

    @GetMapping("getQBS")
    public ResponseMessage getSingleQuestionBySno(){
        return questionService.messageGetSingleQuestionBySno(50);
    }

    @GetMapping("addQ")
    public ResponseMessage addQuestion(){
        Question question = new Question("啦啦",
                "111",1,1,1);
        return questionService.messageAddQuestion(question);
    }


    @GetMapping("deleteQ")
    public ResponseMessage deleteQuestion(){
        List<Question> questions = questionService.getQuestionByMajorid(1);
        return questionService.messageDeleteQuestion(questions.get(1).getQuestionid());
    }


    @GetMapping("sureQ")
    public ResponseMessage sureQuestionStudent(){
        List<Question> questions = questionService.getQuestionByMajorid(1);
        Question question = questions.get(1);
        return questionService.messageSureQuestionStudent(question.getQuestionid(),8);
    }


}
