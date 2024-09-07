package com.nabaneet.quiz_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuizDto {
    private String topic;
    private Integer numOfQuestions;
    private String quizName;
}
