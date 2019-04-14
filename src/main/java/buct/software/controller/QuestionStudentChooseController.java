package buct.software.controller;

import buct.software.domain.QuestionStudentChoose;
import buct.software.service.QuestionStudentChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionStudentChooseController {
    @Autowired
    QuestionStudentChooseService questionStudentChooseService;

    @GetMapping("cQ")
    public void chooseQuestion(){
        questionStudentChooseService.chooseQuestion(1906033518,11);
    }

    @GetMapping("getQSCByQidSno")
    public void getChoiceByQidSno(){
        QuestionStudentChoose questionStudentChoose = questionStudentChooseService.getChoiceByQidSno(1907102645,11);
        System.out.println(questionStudentChoose);
    }

    @GetMapping("getQSCBySno")
    public void getChoiceBySno(){
        List<QuestionStudentChoose> questionStudentChooses = questionStudentChooseService.getChoiceBySno(10);
        for(int i=0;i<questionStudentChooses.size();i++){
            System.out.println(questionStudentChooses.get(i));
        }
    }

    @GetMapping("getQSCByTno")
    public void getChoiceByTno(){
        List<QuestionStudentChoose> questionStudentChooses = questionStudentChooseService.getChoiceByTno(1);
        for(int i=0;i<questionStudentChooses.size();i++){
            System.out.println(questionStudentChooses.get(i));
        }
    }
}
