package buct.software.controller;

import buct.software.domain.QuestionStudentChoose;
import buct.software.service.QuestionStudentChooseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
