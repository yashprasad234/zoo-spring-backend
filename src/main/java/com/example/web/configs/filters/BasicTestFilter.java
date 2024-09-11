package com.example.web.configs.filters;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.web.dto.RequestDTO.UserSignupInputs;
import com.example.web.entities.User;
import com.example.web.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BasicTestFilter extends HttpFilter {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private final UserService userService;
	
	@Autowired
	private final BCryptPasswordEncoder passwordEncoder;
	
	public BasicTestFilter(UserService userService, BCryptPasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

		String requestURI = request.getRequestURI();
        
        // Bypass filter for permitAll routes
        if (requestURI.equals("/login") || requestURI.equals("/signup")) {
            // Skip the authentication and authorization checks for permitAll routes
            chain.doFilter(request, response);
            return;
        }
		
		System.out.println("Inside Basic Test filter");
        UserSignupInputs token = extractUsernameAndPasswordFrom(request);  // (1)

        if (notAuthenticated(token)) {  // (2)
            // either no or wrong username/password
            // unfortunately the HTTP status code is called "unauthorized", instead of "unauthenticated"
        	System.out.println("Not authenticated");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401.
            return;
        }

        if (notAuthorized(token, request)) { // (3)
            // you are logged in, but don't have the proper rights
        	System.out.println("not authorized");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // HTTP 403
            return;
        }
        
        User user = userService.getUserByUsername(token.getUsername());
//        UsernamePasswordAuthenticationToken authToken = 
//            new UsernamePasswordAuthenticationToken(user, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
//        SecurityContextHolder.getContext().setAuthentication(authToken);
        
        UsernamePasswordAuthenticationToken authReq
        = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(authReq);

        // allow the HttpRequest to go to Spring's DispatcherServlet
        // and @RestControllers/@Controllers.
        System.out.println("Authenticated and authorized");
        chain.doFilter(request, response); // (4)
    }

	private UserSignupInputs extractUsernameAndPasswordFrom(HttpServletRequest request) {
        UserSignupInputs userDet = new UserSignupInputs(request.getHeader("X-Email"), request.getHeader("X-Password"));
        System.out.println("Extracted email: " + userDet.getUsername() + " and password: " + userDet.getPassword());
        return userDet;
    }


    private boolean notAuthenticated(UserSignupInputs token) {
        // compare the token with what you have in your database...or in-memory...or in LDAP...
    	System.out.println("starting authentication");
    	User user = userService.getUserByUsername(token.getUsername());
    	System.out.println("user password from db: " + user.getPassword());
    	if(user != null) {
    		System.out.println("User found");
    		if(passwordEncoder.matches(token.getPassword(), user.getPassword())) {
    			System.out.println("User is authenticated");
    			return false;
    		}
    		System.out.println("User is not authenticated");
//    		return true;
    	}
        return true;
    }

    private boolean notAuthorized(UserSignupInputs token, HttpServletRequest request) {
    	User user = userService.getUserByUsername(token.getUsername());
        if(user != null) {
            // Add your authorization logic here (for example, based on user roles)
            if (user.getRole().equals("USER")) { // Example check for admin role
                System.out.println("User is authorized");
                return false; // Authorized
            }
        }
        System.out.println("User is not authorized");
        return true; // Not authorized
    }
	
}
