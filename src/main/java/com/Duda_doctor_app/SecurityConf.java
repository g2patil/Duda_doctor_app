package com.Duda_doctor_app;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import model.MyUserDetailService;

@Configuration
@EnableWebSecurity
//@EnableJdbcHttpSession
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
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http
	            .csrf(csrf -> csrf.disable()) // Disable CSRF protection (consider enabling in production)
	            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configure CORS
	            .sessionManagement(session -> session
	                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
	                .maximumSessions(1)// Create session only if required
	            )
	            
	            .authorizeHttpRequests(authz -> authz
	               // .requestMatchers("/adnya/home", "/adnya/login", "/adnya/users/find/**", "/adnya/cust").permitAll() // Public endpoints
	                .requestMatchers("/register/opd","/adnya/patient/search", "/register/patient").hasRole("USER") // USER role required
	                .requestMatchers("/adnya/admin/home", "/adnya/users").hasRole("SUPER") // SUPER role required
	                .requestMatchers("/adnya/login", "/adnya/logout").permitAll() // Allow access to login and logout
	                .anyRequest().authenticated() // All other requests require authentication
	            )
	            .formLogin(formLogin -> formLogin.permitAll()) // Permit all access to the form login page
	            .logout(logout -> logout
	                .logoutUrl("/adnya/logout")
	                .deleteCookies("JSESSIONID") 
	                // Optional: delete cookies upon logout
	                .permitAll() // Permit access to logout
	            )
	            .build();
	    }

	    
	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	     //   configuration.setAllowedOrigins(Collections.singletonList()); // Adjust this URL as needed
	        configuration.setAllowedOrigins(Arrays.asList(
	                "http://192.168.1.114:8081",
	                "http://localhost:8081",
	                "http://localhost:3000",
	                "http://10.0.2.2:3000",
	                "http://192.168.1.114:8082"
	            ));
	        
	        
	        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
	        configuration.setAllowCredentials(true); // Allow credentials (e.g., cookies)
	        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept")); // Allow headers as needed
	        
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration); // Apply CORS configuration to all endpoints
	        
	        return source;
	    }

	
	    
	    
	    /*
	    
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return
	    	http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(authz -> authz
	            		 .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	                .requestMatchers("/adnya/login").permitAll()
	              //  .requestMatchers("/adnya/home").authenticated()
	                .requestMatchers("/adnya/admin/home").hasRole("USER")
	                .requestMatchers("/adnya/patient/search").hasRole("USER")
	                .requestMatchers("/adnya/cust").permitAll()
					.requestMatchers("/adnya/user/home").permitAll()
					.requestMatchers("/adnya/login").permitAll()
					.requestMatchers("/adnya/users").hasRole("SUPER")//.permitAll();
					.requestMatchers("/adnya/users/find/**").permitAll()
					.requestMatchers("/adnya/patient/find/**").permitAll()
					.requestMatchers("/register/user").permitAll()
					.requestMatchers("/register/patient").permitAll()
					.requestMatchers("/adnya/register/patient").permitAll()
					.requestMatchers("/adnya/register/opd").permitAll()
					.requestMatchers("/adnya/register/user").permitAll()
					.requestMatchers("/adnya/register/bldg").permitAll()
					.requestMatchers("/adnya/home").hasRole("USER")
					.requestMatchers("/adnya/admin/home").hasRole("SUPER")
	                // .requestMatchers("/adnya/patient/search").hasRole("USER")
	                //.requestMatchers("/adnya/patient/search").permitAll()
	                .anyRequest().authenticated()
	               
	            )
	         	.formLogin(formLogin ->formLogin.permitAll())
	         	  .logout(logout -> logout
	         	            .logoutUrl("/adnya/logout")  // URL to trigger logout
	         	            .addLogoutHandler(customLogoutHandler)
	         	            .logoutSuccessUrl("/adnya/login?logout")  // Redirect to login page after logout
	         	            .invalidateHttpSession(true)  // Invalidate the session
	         	            .deleteCookies("JSESSIONID")  // Delete the session cookie
	         	            .permitAll()
	         	        )
				.build();
	    } 
 
	    */
	
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
