package buct.software.controller;

import buct.software.domain.Question;
import buct.software.service.QuestionService;
import buct.software.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuzhongrui
 * 用来测试接口是否正常工作
 */

@RestController
public class QuestionController {
    @Autowired
    private QuestionService questionService;


    @GetMapping("getQBM")
    public ResponseMessage getQuestionByMajorid(){
        return questionService.messageGetQuestionByMajorid(1);
    }


    @GetMapping("getPQBM")
    public ResponseMessage getPartQuestionByMajorid(){
        return questionService.messageGetPartQuestionByMajorid(1);
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
