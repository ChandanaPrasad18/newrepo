package com.te.jspiders.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.te.jspiders.security.filter.SecurityFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;
	private final SecurityFilter securityFilter;

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// its a mtd called to perform encoding of password from dto
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		super.configure(auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/private/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/private/employee/**").hasAnyRole("ADMIN", "EMPLOYEE");
		http.authorizeRequests().antMatchers("/private/trainer/**").hasAnyRole("ADMIN", "TRAINER");
		http.authorizeRequests().antMatchers("/private/student/**").hasAnyRole("ADMIN", "STUDENT");
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
