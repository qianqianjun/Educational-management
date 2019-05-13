package buct.software.controller;

import buct.software.domain.*;
import buct.software.service.QuestionService;
import buct.software.service.QuestionStudentChooseService;
import buct.software.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import buct.software.service.QuestionScoreService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class QuesitonScoreController {
    @Autowired
    QuestionScoreService questionScoreService;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionStudentChooseService questionStudentChooseService;
    @Autowired
    StudentService studentService;
    @RequestMapping(value = "/StuScore")
    public String StuScore(HttpServletRequest request,
                           Map<String,Object> map){
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        int sno = ((User)user).getAccount();
        QuestionScore questionScore = questionScoreService.getQuestionScoreBySno(sno);

        double total = 0;

        if(questionScore==null)
            total = -1;
        else{
            total =  questionScore.getEarlyperformance()*0.1+questionScore.getMidexam()*0.2+questionScore.getThesisanswer()*0.3+questionScore.getPaper()*0.3+questionScore.getExtracredit()*0.1;
        }

        map.put("total",total);
        map.put("quesScore",questionScore);

        return "StuScore";
    }

    @RequestMapping(value = "/StuScoreMobile")
    public String StuScoreMobile(HttpServletRequest request,
                           Map<String,Object> map){
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        int sno = ((User)user).getAccount();
        QuestionScore questionScore = questionScoreService.getQuestionScoreBySno(sno);

        double total = 0;

        if(questionScore==null)
            total = -1;
        else{
            total =  questionScore.getEarlyperformance()*0.1+questionScore.getMidexam()*0.2+questionScore.getThesisanswer()*0.3+questionScore.getPaper()*0.3+questionScore.getExtracredit()*0.1;
        }

        map.put("total",total);
        map.put("quesScore",questionScore);

        return "StuScoreMobile";
    }





    @RequestMapping(value =  "/TeaAddScore")
    public String TeaAddScore(HttpServletRequest request,Map<String,Object>map){
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        int tno = ((User)user).getAccount();
        List<Question> questions = questionService.getQuestionByTno(tno);
        List<QuestionStudentChoose> questionStudentChooses = new ArrayList<>();
        for(int i=0;i<questions.size();i++){
            if (questions.get(i).getSno()!=-1){
                questionStudentChooses.add(new QuestionStudentChoose(questions.get(i).getQuestionid(),questions.get(i).getSno() ) );
            }
        }
        map.put("choices",questionStudentChooses);
        return "TeaAddScore";
    }

    @RequestMapping(value =  "/TeaAddScoreMobile")
    public String TeaAddScoreMobile(HttpServletRequest request,Map<String,Object>map){
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        int tno = ((User)user).getAccount();
        List<Question> questions = questionService.getQuestionByTno(tno);
        List<QuestionStudentChoose> questionStudentChooses = new ArrayList<>();
        for(int i=0;i<questions.size();i++){
            if (questions.get(i).getSno()!=-1){
                questionStudentChooses.add(new QuestionStudentChoose(questions.get(i).getQuestionid(),questions.get(i).getSno() ) );
            }
        }
        map.put("choices",questionStudentChooses);
        return "TeaAddScoreMobile";
    }

    @RequestMapping(value = "/TeaAddScore",method = RequestMethod.POST)
    public String TeaAddScore(HttpServletRequest request,
                             @RequestParam("sno")int sno,
                             @RequestParam("questionid")int questionid,
                             @RequestParam("earlyperformance")int earlyperformance,
                             @RequestParam("midexam")int midexam,
                             @RequestParam("thesisanswer")int thesisanswer,
                             @RequestParam("paper")int paper,
                             @RequestParam("extracredit")int extracredit){
       //判断有无打过分
        QuestionScore questionScoreCheck = questionScoreService.getQuestionScoreBySno(sno);
       if(questionScoreCheck!=null){
           return "redirect:/TeaAddScore";
       }

        QuestionScore questionScore = new QuestionScore();
       questionScore.setSno(sno);
       questionScore.setQuestionid(questionid);
       questionScore.setEarlyperformance(earlyperformance);
       questionScore.setMidexam(midexam);
       questionScore.setThesisanswer(thesisanswer);
       questionScore.setPaper(paper);
       questionScore.setExtracredit(extracredit);
       questionScoreService.addQuestionScore(questionScore);
       return "redirect:/TeaAddScore";
    }

    @RequestMapping(value = "/TeaAddScoreMobile",method = RequestMethod.POST)
    public String TeaAddScoreMobile(HttpServletRequest request,
                              @RequestParam("sno")int sno,
                              @RequestParam("questionid")int questionid,
                              @RequestParam("earlyperformance")int earlyperformance,
                              @RequestParam("midexam")int midexam,
                              @RequestParam("thesisanswer")int thesisanswer,
                              @RequestParam("paper")int paper,
                              @RequestParam("extracredit")int extracredit){
        //判断有无打过分
        QuestionScore questionScoreCheck = questionScoreService.getQuestionScoreBySno(sno);
        if(questionScoreCheck!=null){
            return "redirect:/TeaAddScoreMobile";
        }

        QuestionScore questionScore = new QuestionScore();
        questionScore.setSno(sno);
        questionScore.setQuestionid(questionid);
        questionScore.setEarlyperformance(earlyperformance);
        questionScore.setMidexam(midexam);
        questionScore.setThesisanswer(thesisanswer);
        questionScore.setPaper(paper);
        questionScore.setExtracredit(extracredit);
        questionScoreService.addQuestionScore(questionScore);
        return "redirect:/TeaAddScoreMobile";
    }




    //Back

    @RequestMapping(value = "/ManageLookThroughGrade")
    public String ManageLookThroughGrade(HttpServletRequest request,
                                         Map<String,Object> map){
        List<QuestionScore> questionScores = questionScoreService.getAllQuestionScore();
        map.put("scoreInfos",questionScores);
        return "ManageLookThroughGrade";
    }


    @RequestMapping(value = "/ManageScore")
    public String ManageScore(HttpServletRequest request,
                              @RequestParam("sno")int sno,
                              Map<String,Object> map){
        HttpSession session = request.getSession();
        Object isChangedObject = session.getAttribute("gradeIsChanged");
        Object hasChangedObject = session.getAttribute("gradeHasChanged");
        Boolean hasChanged = (Boolean)hasChangedObject;

        if(isChangedObject == null || hasChangedObject==null){
            session.setAttribute("gradeIsChanged",false);
        }
        else{
            if(hasChanged==true){
                session.setAttribute("gradeHasChanged",false);
            }
            else {
                session.setAttribute("gradeIsChanged",false);
            }
        }

//        System.out.println(isChangedObject);
        QuestionScore questionScore = questionScoreService.getQuestionScoreBySno(sno);
        map.put("Score",questionScore);
        return "ManageScore";
    }

    @RequestMapping(value = "/ManageScore",method = RequestMethod.POST)
    public String ManageScore(HttpServletRequest request,
                              @RequestParam("sno")int sno,
                              @RequestParam("questionid")int questionid,
                              Map<String,Object> map,
                              @RequestParam("earlyperformance")int earlyperformance,
                              @RequestParam("midexam")int midexam,
                              @RequestParam("thesisanswer")int thesisanswer,
                              @RequestParam("paper")int paper,
                              @RequestParam("extracredit")int extracredit){
        QuestionScore questionScore = new QuestionScore();
        questionScore.setSno(sno);
        questionScore.setQuestionid(questionid);
        questionScore.setEarlyperformance(earlyperformance);
        questionScore.setMidexam(midexam);
        questionScore.setThesisanswer(thesisanswer);
        questionScore.setPaper(paper);
        questionScore.setExtracredit(extracredit);
        boolean isChanged = questionScoreService.changeQuestionScore(questionScore);
        HttpSession session = request.getSession();
        session.setAttribute("gradeIsChanged",isChanged);
        session.setAttribute("gradeHasChanged",true);
        return "redirect:/ManageScore"+"?sno="+sno;
    }


    //一下皆为测试接口
//    @GetMapping("addQC")
//    public void addQuestionScore(){
//        QuestionScore q = new QuestionScore(3333,2,3,4,5,6,7);
//        questionScoreService.addQuestionScore(q);
//    }
//
//    @GetMapping("getQSBS")
//    public void getQuestionScoreBySno(){
//        System.out.println(questionScoreService.getQuestionScoreBySno(3333));
//    }
//
//    @GetMapping("getQSBT")
//    public void getQuestionScoreByTno(){
//        System.out.println(questionScoreService.getQuestionScoreByTno(1));
//    }

}

