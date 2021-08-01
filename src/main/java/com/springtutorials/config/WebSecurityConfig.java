package com.springtutorials.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.springtutorials.service.authentication.MyDBAuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyDBAuthenticationService myDBAuthenticationService;
	
	@Autowired
	protected void configureGloble(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.inMemoryAuthentication().withUser("user1").password("1").roles("MEMBER", "CUSTOMER");
		auth.inMemoryAuthentication().withUser("admin1").password("1").roles("MEMBER", "CUSTOMER", "ADMIN");
		
		auth.userDetailsService(myDBAuthenticationService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/admin-info","/login", "/logout").permitAll();
		
		http.authorizeRequests().antMatchers("/")//.hasRole("CUSTOMER");
			.access("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MEMBER', 'ROLE_ADMIN')");
		
		http.authorizeRequests().antMatchers("/admin")//.hasRole("ADMIN");
			.access("hasAnyRole('ROLE_ADMIN')");
		
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/404");
		
		http.authorizeRequests().and().formLogin()
			.loginProcessingUrl("/spring_security_check")
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login?error=true")
			.usernameParameter("username")
			.passwordParameter("password")
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
	}
	
	
}
