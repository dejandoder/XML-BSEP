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

import com.eureka.common.security.JwtConfig;

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
		   //.antMatchers("/admin/test").permitAll()  
		   .antMatchers(HttpMethod.POST, "/auth/login").permitAll()  
		   .antMatchers("/auth/soap/**").permitAll()
		   .antMatchers("/auth/admin/**").hasRole("ADMIN")
		   // must be an admin if trying to access admin area (authentication is also required here)
		   .antMatchers("/mess/**").permitAll()
		   .antMatchers("/acc/soap/**").hasRole("AGENT")
		   //.antMatchers("/acc/admin/**").hasRole("ADMIN")
		   .antMatchers("/acc/all/**").permitAll()
		   .antMatchers("/res/**").permitAll()
		   //.antMatchers("/acc/**").permitAll()
		   //.antMatchers("/auth/**").permitAll()
		   // Any other request must be authenticated
		   .anyRequest().authenticated(); 
	}
	
	@Bean
  	public JwtConfig jwtConfig() {
    	   return new JwtConfig();
  	}
}