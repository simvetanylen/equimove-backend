package com.equimove.backend.config;

import com.equimove.backend.filter.AuthenticationProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationProcessingFilter authenticationProcessingFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().antMatcher("/**").addFilter(authenticationProcessingFilter).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
