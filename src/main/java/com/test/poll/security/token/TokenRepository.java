package com.test.poll.security.token;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface TokenRepository extends CrudRepository<Token, String> {
    Token findByToken(String token);
}