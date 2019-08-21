package com.test.poll.dao;


import com.test.poll.domains.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RestResource(exported = false)
public interface AnswerRepository extends MongoRepository<Answer, String> {

    List<Answer> findByIdPoll(String idPoll);
}
