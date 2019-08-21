package com.test.poll.domains.dto;


import lombok.Data;

@Data
public class LoginResponseDto {
    String token;

    String name;

    String username;

    String idUser;

    String rol;
}
