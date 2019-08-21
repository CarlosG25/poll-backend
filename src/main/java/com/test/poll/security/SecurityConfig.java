package com.test.poll.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class SecurityConfig {

    @Value("${jwt-seceret}")
    private String jwtSeceret;

    @Value("${issuer}")
    private String issuer;

    @Value("${salt-pass}")
    private Integer saltPass;

    @Value("${expirate}")
    private Integer expirate;
}
