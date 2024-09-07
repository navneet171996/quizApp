package com.nabaneet.question_service.dto;

import com.nabaneet.question_service.entities.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionDto {

    public QuestionDto(Question question){
        this.question = question.getQues();
        this.option1 = question.getOption1();
        this.option2 = question.getOption2();
        this.option3 = question.getOption3();
        this.option4 = question.getOption4();
        this.topic = question.getTopic();
    }

    private String topic;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer correctOption;
}
