package com.test.poll.domains;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "polls")
public class Poll {

    @Id
    private String _id;

    private String name;

    private String description;

    private String status;

    private String idUser;

    private List<Answer> answers;

    private LocalDateTime createdAt = LocalDateTime.now();

    private  LocalDateTime updatedAt = LocalDateTime.now();
}
