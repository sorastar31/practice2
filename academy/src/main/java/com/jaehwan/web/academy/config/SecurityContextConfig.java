package com.jaehwan.web.academy.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@ComponentScan(basePackages="com.jaehwan.web.academy.config")
@EnableWebSecurity
public class SecurityContextConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private BasicDataSource dataSource;	
	//@Autowired
	//private AuthenticationSuccessHandler successHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.headers()
				.frameOptions()
				.sameOrigin()
				.and()
			.csrf()
				.disable()
			.authorizeRequests()
				.antMatchers("/*/admin/**").hasRole("ADMIN, TEACHER")
				.anyRequest().permitAll()
				.and()
			.formLogin()
				.loginPage("/member/login")
				.loginProcessingUrl("/member/login")
				.defaultSuccessUrl("/index")
				//.successHandler(successHandler)
				.and()
			.logout()
				.logoutUrl("/member/logout")
				.logoutSuccessUrl("/index");
		
			/*.authorizeRequests()
			.antMatchers("/student/**").hasAnyRole("ADMIN, STUDENT")
			.antMatchers("/teacher/**").hasAnyRole("ADMIN, TEACHER")
			.antMatchers("/academy/**").hasAnyRole("ADMIN, ACADEMY")
			.antMatchers("/admin/**").hasRole("ADMIN");*/
		
	}
	
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//jdbc : dao를 이용한 사용자 정보
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
		//jdbc : query를 직접 사용한 사용자 정보
		/*auth.jdbcAuthentication()
			.dataSource(dataSource)
			//1. 내가 쿼리를 만들어서 제공
			//2. 약속된 인터페이스로 구현된 사용자정보 제공 객체
			.usersByUsernameQuery("select id, pwd password, 1 enabled from Member where id=?")
			.authoritiesByUsernameQuery("select memberId id, roleName authority from MemberRole where memberId=?")
			.passwordEncoder(new BCryptPasswordEncoder());*/
				
		
		//UserBuilder users = User.builder();
		/*auth.inMemoryAuthentication()
		.withUser(users
				.username("nayo")
				.password("111")
				.roles("TEACHER"))
		.withUser(users
				.username("woghks2045")
				.password("111")
				.roles("TEACHE"));*/
	}
}
