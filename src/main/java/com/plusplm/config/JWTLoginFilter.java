package com.plusplm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import com.plusplm.model.User;
import com.plusplm.repository.UserMongoRepository;
import com.plusplm.repository.UserSearchRepository;

public class JWTLoginFilter {

	private JWTLoginFilter() {
	}

	@Configuration
	@EnableWebSecurity
	public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

		@Autowired
		private MySavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

		@Autowired
		UserSearchRepository userSearchRepository;

		@Autowired
		UserMongoRepository userRepository;

		@Autowired
		private UserDetailsService userDetailsService;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
					.authorizeRequests().antMatchers("/api/v1/*").permitAll().and().formLogin()
					.successHandler(authenticationSuccessHandler)
					.failureHandler(new SimpleUrlAuthenticationFailureHandler()).and().logout();
		}

		@Bean
		public MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler() {
			return new MySavedRequestAwareAuthenticationSuccessHandler();
		}

		@Bean
		public SimpleUrlAuthenticationFailureHandler myFailureHandler() {
			return new SimpleUrlAuthenticationFailureHandler();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authenticationProvider());
		}

		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
			authProvider.setUserDetailsService(userDetailsService);
			authProvider.setPasswordEncoder(encoder());
			return authProvider;
		}

		@Override
		@Bean
		public UserDetailsService userDetailsService() {
			return new UserDetailsService() {
				@Override
				public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//					User user = userRepository.findByUsername(userName);
//					if (user == null) {
//						throw new UsernameNotFoundException(userName);
//					}
					return null;
//					return user;
				}
			};
		}

		@Bean
		public PasswordEncoder encoder() {
			return new BCryptPasswordEncoder(11);
		}
	}

}
