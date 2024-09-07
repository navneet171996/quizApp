package com.nabaneet.quiz_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionDto {

    private String topic;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer correctOption;
}
