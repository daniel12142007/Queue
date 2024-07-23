package com.example.queue.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.queue.service.AuthUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final AuthUserDetailsService authUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String httpHeader = request.getHeader("Authorization");
        if (httpHeader != null && !httpHeader.isBlank() && httpHeader.startsWith("Bearer")) {
            try {

                String jwt = httpHeader.substring(7);
                String username = jwtUtils.testingToken(jwt);
                UserDetails userDetails = authUserDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (JWTVerificationException jwtVerificationException) {
                response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Jwt invalid");
            }
        }
        filterChain.doFilter(request, response);
    }
}