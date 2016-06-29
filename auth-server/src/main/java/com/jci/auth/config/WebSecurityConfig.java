package com.jci.auth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableWebSecurity
@Order(-20)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	@Override		
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	private DataSource dataSource;

	/**
	 * The OAuth2 tokens are defined in the datasource defined in the
	 * <code>auth-server.yml</code> file stored in the Spring Cloud config
	 * github repository.
	 * 
	 * @return
	 */
	@Bean
	public JdbcTokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Bean
	protected AuthorizationCodeServices authorizationCodeServices() {
		return new JdbcAuthorizationCodeServices(dataSource);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {					
		auth.jdbcAuthentication().dataSource(dataSource).withUser("user")
				.password("password").roles("USER");
		auth.jdbcAuthentication().dataSource(dataSource).withUser("admin")
				.password("password").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {			
		http
			.formLogin().permitAll()
		//.and()
			//.logout()
		.and()
			.requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
		.and()
			.authorizeRequests().anyRequest().authenticated()
		/*	
		.and()
         .requestMatchers().antMatchers("/me")    
        .and()
         .authorizeRequests().antMatchers("/me").access("#oauth2.hasScope('read')")*/;
	}	
	
	
}
