package com.nabaneet.question_service.repositories;

import com.nabaneet.question_service.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select q from Question q where q.topic=:topic")
    List<Question> getQuestionsFromTopic(String topic);

    @Query("select q.id from Question q where q.topic=:topicName order by RAND() limit :numOfQuestions")
    List<Long> getQuestionsForQuiz(String topicName, Integer numOfQuestions);
}
