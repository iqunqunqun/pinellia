package com.ivan.pinellia.secure.filter;


import com.ivan.pinellia.secure.config.WebSecurityConfig;
import com.ivan.pinellia.secure.utils.JwtTokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt auth token filter
 *
 * @author wfnuser
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer ";

    private JwtTokenUtils tokenProvider;

    public JwtAuthenticationTokenFilter(JwtTokenUtils tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwt = resolveToken(request);

        if (jwt != null && !"".equals(jwt.trim()) && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (this.tokenProvider.validateToken(jwt)) {
                /**
                 * get auth info
                 */
                Authentication authentication = this.tokenProvider.getAuthentication(jwt);
                /**
                 * save user info to securityContext
                 */
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * Get token from header
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(WebSecurityConfig.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7, bearerToken.length());
        }
        String jwt = request.getParameter(WebSecurityConfig.AUTHORIZATION_TOKEN);
        if (StringUtils.hasText(jwt)) {
            return jwt;
        }
        return null;
    }
}
