package com.lcwd.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import security.JWTAuthenticationEntryPoint;
import security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private String[] PUBLIC_URLS = {
			"/swagger-ui/**",
			"/webjars/**",
			"/swagger-resources/**",
			"/v2/api-docs"
	};
	
	private String [] adminURL = {
			"/users/**",
			"/products/**",
			"/categories/**",
			"/carts/**",
			"/orders/**"
	};
	
//	@Autowired
//	public void configGlobal(AuthenticationManagerBuilder manager) throws Exception {
//		manager
//		.inMemoryAuthentication()
//		.withUser("denis")
//		.password(passwordEncoder.encode("123"))
//		.authorities("ROLE_ADMIN")
//		.and()
//		.withUser("durgesh")
//		.password(passwordEncoder.encode("123"))
//		.authorities("ROLE_USER");
//	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		
		http
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers(HttpMethod.POST,adminURL)
		.permitAll()
		.antMatchers(HttpMethod.GET,adminURL)
		.permitAll()
		.antMatchers(PUBLIC_URLS)
		.permitAll()
		.antMatchers(HttpMethod.DELETE,"/users/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(jwtAuthenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.authenticationProvider(doAuthenticationProvider());
		
//		http.authorizeHttpRequests()
//	   //.antMatchers(HttpMethod.GET,"/users/**").hasAnyAuthority("ROLE_USER")
//	   //.antMatchers(HttpMethod.POST,"/users").permitAll()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.httpBasic();
		
		return http.build();
	}
	
	@Bean
	public DaoAuthenticationProvider doAuthenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
