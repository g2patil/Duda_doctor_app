package com.Duda_doctor_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import model.MyUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConf {
	
	@Autowired
	private MyUserDetailService  userDetailService;

	    @Bean
		public UserDetailsService userDetailsService() {
		return userDetailService;
	}
	    
	    @Bean
	    public AuthenticationManager authenticationManager(
	                                 AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }
	    



		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity httpsecurity) throws Exception {
			return httpsecurity
					 .csrf(csrf -> csrf.disable())
				    .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable() )
					.authorizeHttpRequests(registery->{
				registery.requestMatchers("/adnya/cust").permitAll();
				registery.requestMatchers("/adnya/user/home").permitAll();
				registery.requestMatchers("/adnya/login").permitAll();
				registery.requestMatchers("/adnya/users").permitAll();
				registery.requestMatchers("/adnya/users/find/**").permitAll();
				registery.requestMatchers("/adnya/patient/find/**").permitAll();
				registery.requestMatchers("/register/user").permitAll();
				registery.requestMatchers("/register/patient").permitAll();
				registery.requestMatchers("/adnya/register/patient").permitAll();
				registery.requestMatchers("/adnya/register/opd").permitAll();
				registery.requestMatchers("/adnya/register/user").permitAll();
				registery.requestMatchers("/adnya/register/bldg").permitAll();
				registery.requestMatchers("/adnya/home").permitAll();
				registery.requestMatchers("/adnya/admin/home").hasRole("SUPER");
			})     	.formLogin(formLogin ->formLogin.permitAll())
					.build();
		}
	    
	@Bean 
	public AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
	}

}
/*	@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpsecurity) throws Exception {
	return httpsecurity.authorizeHttpRequests(registery->{
		registery.requestMatchers("/adnya/cust").permitAll();
		registery.requestMatchers("/adnya/user/home").permitAll();
		registery.requestMatchers("/adnya/home").permitAll();
		registery.requestMatchers("/adnya/admin/home").hasRole("SUPER");
	})      .formLogin(formLogin ->formLogin.permitAll())
			.build();
} 
@Bean
public InMemoryUserDetailsManager userDetailsService() {
  	UserDetails normalUser =User.builder()
			.username("jitu")
			.password("$2a$12$KxLgfim/p4aqFcRHqNNAze4YE.1gXOO.zTSiV.qOeshbyKhq/J7J.")
			.roles("ADMIN")
			.build();
  	//return normalUser;
	return new InMemoryUserDetailsManager(normalUser);
}
*/