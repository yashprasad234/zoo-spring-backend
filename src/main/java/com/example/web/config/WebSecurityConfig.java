package com.example.web.config;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.web.config.filters.BasicTestFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration configuration = new CorsConfiguration();
    	configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
    	configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS"));
    	configuration.setAllowedHeaders(Arrays.asList("*"));
    	configuration.setAllowCredentials(true);
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", configuration);
    	return source;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, BasicTestFilter basicTestFilter) throws Exception {
		return http
			.csrf(csrf -> csrf.disable())
			.httpBasic(httpBasic -> httpBasic.disable())
			.formLogin((form) -> form.disable())
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/login", "/signup")
				.permitAll()
				.anyRequest()
				.authenticated()
			)
			.addFilterBefore(basicTestFilter, UsernamePasswordAuthenticationFilter.class)
			.build();
	}
    
    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
