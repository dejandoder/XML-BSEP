package com.eureka.zuul.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity 	// Enable security config. This annotation denotes config for spring security.
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtConfig jwtConfig;
 
	@Override
  	protected void configure(HttpSecurity http) throws Exception {
    	   http
		.csrf().disable()
		    // make sure we use stateless session; session won't be used to store user's state.
	 	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 	
		.and()
		    // handle an authorized attempts 
		    .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)) 	
		.and()
		   // Add a filter to validate the tokens with every request
		   .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
		// authorization requests config
		.authorizeRequests()
		   // allow all who are accessing "auth" service  
		   .antMatchers(HttpMethod.POST, "/auth/login").permitAll()  
		   .antMatchers(HttpMethod.POST, "/auth/registration").permitAll()  
		   .antMatchers("/auth/soap/**").permitAll()
		   .antMatchers("/**/admin/**").hasRole("ADMIN")
		   .antMatchers("/**/agent/**").hasRole("AGENT")
		   .antMatchers("/**/user/**").hasRole("USER")
		   .antMatchers("/**/all/**").permitAll()
		   .anyRequest().authenticated(); 
	}
	
	@Bean
  	public JwtConfig jwtConfig() {
    	   return new JwtConfig();
  	}
}