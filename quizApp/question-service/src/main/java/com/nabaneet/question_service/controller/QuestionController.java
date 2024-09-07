package com.nabaneet.question_service.controller;

import com.nabaneet.question_service.dto.QuestionDto;
import com.nabaneet.question_service.dto.ResponseDto;
import com.nabaneet.question_service.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping(path = "/addQuestion")
    public ResponseEntity<Long> addQuestion(@RequestBody QuestionDto questionDto){
        return new ResponseEntity<>(questionService.addQuestion(questionDto), HttpStatus.OK);
    }

    @PostMapping(path = "/addMultipleQuestions")
    public ResponseEntity<List<Long>> addMultipleQuestions(@RequestBody List<QuestionDto> questionDtos){
        return new ResponseEntity<>(questionService.addMultipleQuestions(questionDtos), HttpStatus.OK);
    }

    @GetMapping(path = "/allQuestions")
    public ResponseEntity<List<QuestionDto>> getAllQuestions(){
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping(path = "/category/{topic}")
    public ResponseEntity<List<QuestionDto>> getAllQuestionsFromTopic(@PathVariable String topic){
        return new ResponseEntity<>(questionService.getQuestionsFromTopic(topic), HttpStatus.OK);
    }

    @GetMapping(path = "/getQuestionsForQuiz/{topicName}/{numOfQuestions}")
    public ResponseEntity<List<Long>> getQuestionsForQuiz(@PathVariable String topicName, @PathVariable Integer numOfQuestions){
        return new ResponseEntity<>(questionService.getQuestionsForQuiz(topicName, numOfQuestions), HttpStatus.OK);
    }

    @PostMapping(path = "/getQuestionsById")
    public ResponseEntity<List<QuestionDto>> getQuestionsById(@RequestBody List<Long> questionIds){
        return new ResponseEntity<>(questionService.getQuestionsById(questionIds), HttpStatus.OK);
    }

    @PostMapping(path = "/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<ResponseDto> responses){
        return new ResponseEntity<>(questionService.getScore(responses), HttpStatus.OK);
    }
}
