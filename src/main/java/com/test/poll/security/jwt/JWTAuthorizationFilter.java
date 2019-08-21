package com.test.poll.security.jwt;

import com.test.poll.security.token.impl.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String HTTP_METHOD_OPTIONS = "OPTIONS";
    private final String PREFIX = "Bearer ";

    private static final String[] TO_EXCLUDE = {
            "/api/auth/login"
    };

    @Autowired
    private JwtTokenFactory jwtTokenFactory;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {

        try {
            String currentEndpoint = req.getRequestURI();

            for (String url : TO_EXCLUDE) {
                if (containsIgnoreCase(currentEndpoint, url)) {
                    chain.doFilter(req, res);
                    return;
                }
            }

            this.isOptions(res, req, chain);
            this.validateJwtInHeader(req);

            String jwtToken = req.getHeader(HEADER).replace(PREFIX, "");
            Claims claims = this.jwtTokenFactory.decodeJWT(jwtToken);
            chain.doFilter(req, res);

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            res.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    private static boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }

    private void validateJwtInHeader(HttpServletRequest request) throws ServletException {
        String authenticationHeader = request.getHeader(HEADER);
        boolean existToken = authenticationHeader != null && authenticationHeader.startsWith(PREFIX);

        if (!existToken) {
            throw new ServletException("Missing or invalid Authorization header");
        }
    }

    private void isOptions(HttpServletResponse response, HttpServletRequest request, FilterChain chain) throws IOException, ServletException {
        if (HTTP_METHOD_OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
        }
    }

}
