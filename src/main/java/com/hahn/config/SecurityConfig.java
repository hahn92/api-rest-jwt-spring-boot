package com.hahn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.hahn.service.UserService;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userDService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.cors().and().csrf().disable().authorizeRequests()
	            .antMatchers(HttpMethod.POST, ConfigConstants.SIGN_UP_URL).permitAll()
	            .antMatchers("/login").permitAll() //permitimos el acceso a /login a cualquiera
	            .anyRequest().authenticated()
	            .and()
	            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
	            .addFilter(new JWTAuthorizationFilter(authenticationManager()))
	            // this disables session creation on Spring Security
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDService).passwordEncoder(passwordEnconder());
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
	}
	
	@Bean 
	public BCryptPasswordEncoder passwordEnconder() {
		BCryptPasswordEncoder passwordEnconder = new BCryptPasswordEncoder();
		return passwordEnconder;
	}
	
}


