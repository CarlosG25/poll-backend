package com.test.poll.security.token.impl;


import com.test.poll.security.SecurityConfig;
import com.test.poll.security.jwt.JwtTokenFactory;
import com.test.poll.security.token.Token;
import com.test.poll.security.token.TokenRepository;
import com.test.poll.utils.AssertsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {

    public final static String TOKEN_EMPTY = "";

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private AssertsUtils assertsUtils;

    @Autowired
    private JwtTokenFactory jwtTokenFactory;

    @Autowired
    private SecurityConfig securityConfig;

    public Token save(Token token) {
        this.assertsUtils.isNotNull(token, "token object cannot be null!.");

        String id = token.get_id();
        String tokenStr = token.getToken();
        this.assertsUtils.isNotNull(id, "id cannot be null!.");
        this.assertsUtils.isNotNull(tokenStr, "tokenStr cannot be null!.");

        return this.tokenRepository.save(token);
    }


    public String exisTokenForUser(String idUser) {
        this.assertsUtils.isNotNull(idUser, "idUser can not be null.");
       Optional<Token> token = this.tokenRepository.findById(idUser);

        if(token.isPresent()) {
            return token.get().getToken();
        }
        return TOKEN_EMPTY;
    }

    public void deleteToken(String token) {
        this.assertsUtils.isNotNullOrEmpty(token, "token");
        Token tokenObject = this.tokenRepository.findByToken(token);

        if(tokenObject != null) {
            this.tokenRepository.delete(tokenObject);
        }
    }

    public void deleteTokenByIdUser(String idUser) {
        this.assertsUtils.isNotNullOrEmpty(idUser, "idUser");
        this.tokenRepository.deleteById(idUser);
    }

    public String getToken(String idUser, String username) {
        this.assertsUtils.isNotNull(idUser, "idUser can not be null.");

        String token = this.exisTokenForUser(idUser);

        if (token.equals(TokenService.TOKEN_EMPTY)) {
            String jwtToken = this.jwtTokenFactory.getJWTToken(idUser, username);

            Long ttl = this.securityConfig.getExpirate().longValue();
            this.assertsUtils.isNotNull(ttl, "ttl cannot be null");

            Token tokenObject = new Token();
            tokenObject.set_id(idUser);
            tokenObject.setTtl(ttl);
            tokenObject.setToken(jwtToken);
            this.save(tokenObject);
            return jwtToken;
        }
        return token;
    }
}
