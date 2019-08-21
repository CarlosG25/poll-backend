package com.test.poll.security.token;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "token")
public class Token {

    @Id
    private String _id;

    private Long ttl;

    private String token;
}
