package com.test.poll.services;

import com.test.poll.domains.User;
import com.test.poll.domains.dto.LoginRequestDto;
import com.test.poll.domains.dto.LoginResponseDto;
import com.test.poll.domains.dto.LogoutRequestDto;
import com.test.poll.exceptions.BusinessException;
import com.test.poll.exceptions.UserNotFoundException;
import com.test.poll.security.token.impl.TokenService;
import com.test.poll.utils.AssertsUtils;
import com.test.poll.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class AccessService {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordUtils passwordUtils;

    @Autowired
    private AssertsUtils assertsUtils;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws UserNotFoundException, BusinessException {
        this.assertsUtils.isNotNull(loginRequestDto, "credentials are required!.");
        User user = this.userService.findByUserName(loginRequestDto.getUsername_j());

        String currentPassword = user.getPassword();
        Assert.notNull(currentPassword, "currentPassword cannot be null!.");

        String username = user.getUsername();
        this.assertsUtils.isNotNull(username, "username cannot be null!.");

        String providedPassword = loginRequestDto.getPassword_j();
        this.assertsUtils.isNotNull(providedPassword, "providedPassword cannot be null!.");

        if(!this.passwordUtils.verifyUserPassword(providedPassword, currentPassword)) {
            throw new BusinessException("the provided credentials are not valid!.");
        }

        if (!user.isActive()) {
            throw new BusinessException("the user not is active!.");
        }

        String token = this.tokenService.getToken(user.get_id(), username);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setIdUser(user.get_id());
        loginResponseDto.setName(user.getName());
        loginResponseDto.setToken(token);
        loginResponseDto.setRol(user.getRol());
        loginResponseDto.setUsername(loginRequestDto.getUsername_j());
        return loginResponseDto;
    }

    public LogoutRequestDto logout(LogoutRequestDto logoutRequestDto) {
        this.assertsUtils.isNotNull(logoutRequestDto, "username_j cannot be null!.");
        this.tokenService.deleteTokenByIdUser(logoutRequestDto.getIdUser_j());
        return logoutRequestDto;
    }
}
