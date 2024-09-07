package com.nabaneet.quiz_service.feign;

import com.nabaneet.quiz_service.dto.QuestionDto;
import com.nabaneet.quiz_service.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuestionInterface {

    @GetMapping(path = "/questions/getQuestionsForQuiz/{topicName}/{numOfQuestions}")
    public ResponseEntity<List<Long>> getQuestionsForQuiz(@PathVariable String topicName, @PathVariable Integer numOfQuestions);

    @PostMapping(path = "/questions/getQuestionsById")
    public ResponseEntity<List<QuestionDto>> getQuestionsById(@RequestBody List<Long> questionIds);

    @PostMapping(path = "/questions/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<ResponseDto> responses);
}
