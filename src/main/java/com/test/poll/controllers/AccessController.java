package com.test.poll.controllers;

import com.test.poll.domains.dto.LoginRequestDto;
import com.test.poll.domains.dto.LoginResponseDto;
import com.test.poll.domains.dto.LogoutRequestDto;
import com.test.poll.exceptions.BusinessException;
import com.test.poll.exceptions.UserNotFoundException;
import com.test.poll.services.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/auth")
public class AccessController {

    @Autowired
    private AccessService accessService;

    @PostMapping(value = "login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto)
            throws UserNotFoundException, BusinessException {
        return this.accessService.login(loginRequestDto);
    }

    @PostMapping(value = "logout")
    public LogoutRequestDto logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        return this.accessService.logout(logoutRequestDto);
    }
}

