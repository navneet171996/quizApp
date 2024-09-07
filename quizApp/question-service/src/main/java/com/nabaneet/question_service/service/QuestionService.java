package com.nabaneet.question_service.service;

import com.nabaneet.question_service.dto.QuestionDto;
import com.nabaneet.question_service.dto.ResponseDto;
import com.nabaneet.question_service.entities.Question;
import com.nabaneet.question_service.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<QuestionDto> getAllQuestions(){
        return questionRepository.findAll().stream().map(QuestionDto::new).collect(Collectors.toList());
    }

    public Long addQuestion(QuestionDto questionDto) {
        Question question = new Question();
        question.setQues(questionDto.getQuestion());
        question.setTopic(questionDto.getTopic());
        question.setOption1(questionDto.getOption1());
        question.setOption2(questionDto.getOption2());
        question.setOption3(questionDto.getOption3());
        question.setOption4(questionDto.getOption4());
        question.setCorrectOption(questionDto.getCorrectOption());

        Question savedQuestion = questionRepository.save(question);
        return savedQuestion.getId();
    }

    public List<QuestionDto> getQuestionsFromTopic(String topic) {
        return questionRepository.getQuestionsFromTopic(topic).stream().map(QuestionDto::new).collect(Collectors.toList());
    }

    public List<Long> getQuestionsForQuiz(String topicName, Integer numOfQuestions) {
        return questionRepository.getQuestionsForQuiz(topicName, numOfQuestions);
    }

    public List<Long> addMultipleQuestions(List<QuestionDto> questionDtos) {
        List<Long> questionList = new ArrayList<>();
        for (QuestionDto questionDto : questionDtos) {
            questionList.add(addQuestion(questionDto));
        }
        return questionList;
    }

    public List<QuestionDto> getQuestionsById(List<Long> questionIds) {
        List<QuestionDto> questionDtos = new ArrayList<>();
        questionIds.forEach(questionId -> {
            QuestionDto questionDto = new QuestionDto(questionRepository.findById(questionId).get());
            questionDtos.add(questionDto);
        });
        return questionDtos;
    }

    public Integer getScore(List<ResponseDto> responses) {
        int count = 0;
        for(ResponseDto response:responses){
            Question question = questionRepository.findById(response.getQuestionId()).get();
            if(Objects.equals(question.getCorrectOption(), response.getResponse()))
                count++;
        }
        return count;
    }
}
