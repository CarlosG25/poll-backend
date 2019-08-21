package com.test.poll.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GlobalConfiguration {

    @Getter
    @Value("${salt.password}")
    String saltPassword;

    @Getter
    @Value("${secret.password}")
    String secretPassword;
}
