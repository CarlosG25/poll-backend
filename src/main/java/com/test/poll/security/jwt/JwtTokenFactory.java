package com.test.poll.security.jwt;

import com.test.poll.security.SecurityConfig;
import com.test.poll.security.token.impl.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenFactory {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private TokenService tokenService;


    public Claims decodeJWT(String jwt) {

        Claims claims = null;
        try {
            String secretKey = securityConfig.getJwtSeceret();
          claims =  Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(jwt).getBody();
        } catch (Exception ex) {
            this.tokenService.deleteToken(jwt);
        }

        return claims;
    }

    public String getJWTToken(String userId, String username) {
        String secretKey = securityConfig.getJwtSeceret();

        java.util.Date now = new java.util.Date();
        java.util.Date expiryDate = new java.util.Date(now.getTime() + securityConfig.getExpirate());

        return Jwts
                .builder()
                .setId(securityConfig.getIssuer())
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256,
                        secretKey.getBytes()).compact();
    }

}
