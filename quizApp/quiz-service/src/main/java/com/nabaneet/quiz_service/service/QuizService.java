package com.nabaneet.quiz_service.service;

import com.nabaneet.quiz_service.dto.QuestionDto;
import com.nabaneet.quiz_service.dto.QuizDto;
import com.nabaneet.quiz_service.dto.ResponseDto;
import com.nabaneet.quiz_service.entities.Quiz;
import com.nabaneet.quiz_service.feign.QuestionInterface;
import com.nabaneet.quiz_service.repositories.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionInterface questionInterface;

    public QuizService(QuizRepository quizRepository, QuestionInterface questionInterface) {
        this.quizRepository = quizRepository;
        this.questionInterface = questionInterface;
    }

    public Long generateQuiz(QuizDto quizDto) {
        Quiz quiz = new Quiz();
        quiz.setName(quizDto.getQuizName());
        List<Long> questionsForQuiz = questionInterface.getQuestionsForQuiz(quizDto.getTopic(), quizDto.getNumOfQuestions()).getBody();

        quiz.setQuestionIds(questionsForQuiz);

        return quizRepository.save(quiz).getId();

    }

    public List<QuestionDto> getQuestionsForQuiz(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).get();
        return questionInterface.getQuestionsById(quiz.getQuestionIds()).getBody();
    }

    public Integer getScore(List<ResponseDto> responses) {
        return questionInterface.getScore(responses).getBody();
    }
}
