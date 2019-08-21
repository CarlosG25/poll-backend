package com.test.poll.dao;


import com.test.poll.domains.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface UserRepository extends MongoRepository<User, String> {
    User findFirst1ByUsername(String username);
}
