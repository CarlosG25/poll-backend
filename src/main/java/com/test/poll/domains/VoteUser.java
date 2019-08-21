package com.test.poll.domains;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "votesForUser")
public class VoteUser {

    @Id
    private String _id;

    private String idPoll;

    private String idAnswer;

    private String idUser;

     private LocalDateTime createdAt = LocalDateTime.now();

    private  LocalDateTime updatedAt = LocalDateTime.now();
}
