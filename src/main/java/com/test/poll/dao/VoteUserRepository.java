package com.test.poll.dao;


import com.test.poll.domains.VoteUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteUserRepository extends MongoRepository<VoteUser, String> {

    VoteUser findByIdUserAndIdPoll(String idUser, String idPoll);
}
