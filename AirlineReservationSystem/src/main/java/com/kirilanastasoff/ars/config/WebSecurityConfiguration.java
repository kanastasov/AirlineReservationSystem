package com.kirilanastasoff.ars.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserDetailsService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(encoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String loginPage = "/login";
		String logoutPage = "/logout";

		http.authorizeRequests().antMatchers(loginPage, "/showCustomerForm", "/register").permitAll()
				.antMatchers("/index", "/flight", "/airplane", "/americanairlines", "/profile").hasAuthority("USER")
				.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated().and().csrf().disable()
				.formLogin().loginPage(loginPage).failureUrl("/login?error=true").defaultSuccessUrl("/", true)
				.usernameParameter("username").passwordParameter("password").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher(logoutPage)).logoutSuccessUrl(loginPage).and()
				.exceptionHandling();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**",
				"/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security",
				"/swagger-ui.html");
	}

}
