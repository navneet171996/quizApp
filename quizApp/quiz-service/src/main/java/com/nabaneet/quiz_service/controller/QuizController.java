package com.nabaneet.quiz_service.controller;

import com.nabaneet.quiz_service.dto.QuestionDto;
import com.nabaneet.quiz_service.dto.QuizDto;
import com.nabaneet.quiz_service.dto.ResponseDto;
import com.nabaneet.quiz_service.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping(path = "/generate")
    public ResponseEntity<Long> generateQuiz(@RequestBody QuizDto quizDto){
        return new ResponseEntity<>(quizService.generateQuiz(quizDto), HttpStatus.OK);
    }

    @GetMapping(path = "/getQuestionsForQuiz/{quizId}")
    public ResponseEntity<List<QuestionDto>> getQuizQuestions(@PathVariable Long quizId){
        return new ResponseEntity<>(quizService.getQuestionsForQuiz(quizId), HttpStatus.OK);
    }

    @GetMapping(path = "/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<ResponseDto> responses){
        return new ResponseEntity<>(quizService.getScore(responses), HttpStatus.OK);
    }
}
