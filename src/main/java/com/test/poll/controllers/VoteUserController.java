package com.test.poll.controllers;

import com.test.poll.domains.VoteUser;
import com.test.poll.exceptions.BusinessException;
import com.test.poll.services.VoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/vote-users")
public class VoteUserController {

    @Autowired
    private VoteUserService voteUserService;

    @PostMapping
    public VoteUser save(@RequestBody VoteUser voteUser) throws BusinessException {
        return this.voteUserService.save(voteUser);
    }

}

