package com.test.poll.domains;

import com.test.poll.domains.enums.Status;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document(collection = "answers")
public class Answer {

    @Id
    private String _id;

    private String idPoll;

    private String value;

    private Integer numberVotes;

    private String status = Status.ACTIVE.name();

    private LocalDateTime createdAt = LocalDateTime.now();

    private  LocalDateTime updatedAt = LocalDateTime.now();

    public Integer getNumberVotes() {
        return this.numberVotes == null? 0: numberVotes;
    }
}
