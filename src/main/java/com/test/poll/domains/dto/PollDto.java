package com.test.poll.domains.dto;

import com.test.poll.domains.Answer;
import lombok.Data;

import java.util.List;

@Data
public class PollDto {
    String namePoll;
    String descriptionPoll;
    String idUser;
    List<Answer> answers;
}
