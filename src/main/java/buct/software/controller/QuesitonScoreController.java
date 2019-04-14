package buct.software.controller;


import buct.software.domain.Question;
import buct.software.domain.QuestionScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import buct.software.service.QuestionScoreService;

@RestController
public class QuesitonScoreController {
    @Autowired
    QuestionScoreService questionScoreService;

    @GetMapping("addQC")
    public void addQuestionScore(){
        QuestionScore q = new QuestionScore(3333,2,3,4,5,6,7);
        questionScoreService.addQuestionScore(q);
    }

    @GetMapping("getQSBS")
    public void getQuestionScoreBySno(){
        System.out.println(questionScoreService.getQuestionScoreBySno(0));
    }

    @GetMapping("getQSBT")
    public void getQuestionScoreByTno(){
        System.out.println(questionScoreService.getQuestionScoreByTno(1));
    }

}

