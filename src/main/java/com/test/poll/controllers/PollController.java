package com.test.poll.controllers;


import com.test.poll.domains.Poll;
import com.test.poll.domains.dto.PollDto;
import com.test.poll.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    @PostMapping
    public PollDto save(@RequestBody PollDto pollDto) {
        return this.pollService.save(pollDto);
    }

    @GetMapping
    public List<Poll> findAll() {
        return this.pollService.findAll();
    }

    @GetMapping("{idPoll}")
    public Poll findById(@PathVariable String idPoll) {
        return  this.pollService.findById(idPoll);
    }

    @GetMapping(value = "user")
    public List<Poll> findAllByIdUser(@RequestParam String idUser) {
        return this.pollService.findAllByIdUser(idUser);
    }
}
