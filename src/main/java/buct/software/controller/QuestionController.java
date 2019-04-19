package buct.software.controller;

import buct.software.domain.*;
import buct.software.service.QuestionService;
import buct.software.service.QuestionStudentChooseService;
import buct.software.service.StudentService;
import buct.software.service.TeacherService;
import buct.software.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhongrui
 */

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private QuestionStudentChooseService questionStudentChooseService;
    @RequestMapping("/StuLookThroughQues")
    public String StuLookThroughQues(HttpServletRequest request,
                                     Map<String,Object> map){
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
                                 Map<String,Object> map,
                                 @RequestParam("questionid") int questionid
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

    @RequestMapping("/TeaLookThroughQues")
    public String TeaLookThroughQues(HttpServletRequest request,
                                     Map<String,Object> map){
        HttpSession session =  request.getSession();
        Object userInfo = session.getAttribute("user");
        User user = (User) userInfo;
        int tno = user.getAccount();
        List<Question> questions = questionService.getQuestionByTno(tno);
        map.put("quesInfos",questions);
        return "TeaLookThroughQues";
    }

    @RequestMapping("/ManageQues")
    public String ManageQues(HttpServletRequest request,
                             Map<String,Object> map){
        List<Question> questions = questionService.getAllQuestions();
        map.put("quesInfos",questions);
        return "ManageQues";
    }
    @RequestMapping(value = "/ManageQues",method = RequestMethod.POST)
    public String ManageQues(HttpServletRequest request,
                             @RequestParam("questionid")int questionid,
                             Map<String,Object> map){
        boolean isDeleted = questionService.deleteStudentQuestion(questionid);
        map.put("isDeleted",isDeleted);
        return "forward:/ManageQues";
    }



    @RequestMapping(value = "/TeaAddQues")
    public String TeaAddQues(
            HttpServletRequest request
    ){
        return "TeaAddQues";
    }
    @RequestMapping(value = "/TeaAddQues",method = RequestMethod.POST)
    public String TeaAddQues(HttpServletRequest request, @RequestParam("topic")String topic,@RequestParam("content")String content, @RequestParam("difficulty")int difficulty,
                             @RequestParam("majorid")int majorid,
                             Map<String,Object>map){
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        int tno = ((User)user).getAccount();
        Question question = new Question();
        question.setTno(tno);

        question.setTopic(topic);
        question.setContent(content);
        question.setDifficulty(difficulty);
        question.setMajorid(majorid);
        boolean isAdded = questionService.addQuestion(question);
        map.put("isAdded",isAdded);
        return "forward/TeaAddQues";
    }



    @RequestMapping(value = "/TeaQuesDetails")
    public String TeaQuesDetails(HttpServletRequest request,@RequestParam("questionid")int questionid,
                                 Map<String,Object>map){
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        int tno = ((User)user).getAccount();
    Question question = questionService.getSingleQuestionByQuestionid(questionid);
    map.put("question",question);
    List<QuestionStudentChoose> questionStudentChooses = questionStudentChooseService.getChoiceByTno(tno);
    map.put("choices",questionStudentChooses);
    return "TeaQuesDetails";
    }

    @RequestMapping(value = "/sureQuesStu")
    public String sureQuesStu(HttpServletRequest  request,
                              @RequestParam("questionid")int questionid,
                              @RequestParam("sno")int sno,
                              Map<String,Object>map){
        boolean isSured = questionService.sureQuestionStudent(questionid,sno);
        map.put("isSured",isSured);
        return "forward:/TeaQuesDetails";
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
