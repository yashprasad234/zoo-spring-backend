package com.example.web.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.web.services.ZooUserDetailservice;

@Configuration
@EnableAspectJAutoProxy
public class ApplicationConfiguration 
{
	
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
    
    @Bean
    AuthenticationProvider authenticationProvider(ZooUserDetailservice detailservice) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(detailservice);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}