package com.test.poll.dao;


import com.test.poll.domains.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RestResource(exported = false)
public interface PollRepository  extends MongoRepository<Poll, String> {
    List<Poll> findAllByIdUser(String idUser);
    List<Poll> findByStatusOrderByCreatedAtDesc(String status);
}
