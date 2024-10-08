package com.example.web.configs.filters;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.example.web.customClasses.MyUserDetails;
import com.example.web.services.JwtService;
import com.example.web.services.UserTokenService;
import com.example.web.services.ZooUserDetailservice;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
	@Autowired
	private final JwtService jwtService;
	@Autowired
    private final ZooUserDetailservice userDetailsService;
	@Autowired
    private final HandlerExceptionResolver handlerExceptionResolver;
	@Autowired
	private final UserTokenService userTokenService;

    public JwtAuthenticationFilter(JwtService jwtService, 
                                   ZooUserDetailservice userDetailsService, 
                                   HandlerExceptionResolver handlerExceptionResolver, UserTokenService userTokenService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.userTokenService = userTokenService;
    }

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
    	System.out.println("Inside Jwt Authentication Filter");
        final String authHeader = request.getHeader("Authorization");
        
        jwtService.getExpirationTime();
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);
            
            if(!userTokenService.checkTokenIsValid(jwt)) {
            	filterChain.doFilter(request, response);
            	throw new IllegalArgumentException("Invalid JWT token");
            }
            

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                MyUserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt, userDetails)) {
		            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + (jwtService.exractRole(jwt)));
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            Collections.singletonList(authority)
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
        	System.out.println(exception.getMessage());
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}