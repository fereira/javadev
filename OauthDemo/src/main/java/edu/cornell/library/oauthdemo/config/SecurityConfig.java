package edu.cornell.library.oauthdemo.config;

 

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Configuration
@ComponentScan
@Lazy
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	      SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler("/");

	      // @formatter:off
	      http.antMatcher("/**")
	          .authorizeRequests(a -> a
	              .antMatchers("/", "/error", "/webjars/**").permitAll()
	              .anyRequest().authenticated()
	          )
	          .exceptionHandling(e -> e
	              .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
	          )
	          .csrf(c -> c
	              .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	          )
	          .logout(l -> l
	              .logoutSuccessUrl("/").permitAll()
	          )
	          .oauth2Login(o -> o
	              .failureHandler((request, response, exception) -> {
	                  request.getSession().setAttribute("error.message", exception.getMessage());
	                  handler.onAuthenticationFailure(request, response, exception);
	              })
	          );
	      // @formatter:on
	  }
    
} 
