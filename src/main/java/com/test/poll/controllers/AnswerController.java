package com.test.poll.controllers;

import com.test.poll.domains.Answer;
import com.test.poll.exceptions.BusinessException;
import com.test.poll.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("{idPoll}/poll")
    public List<Answer> save(@RequestBody String idPoll) throws BusinessException {
        return this.answerService.findAndswerByIdPoll(idPoll);
    }

}

