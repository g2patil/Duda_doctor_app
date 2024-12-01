package model;


import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private MyUserRepository repository;
	
	
	 @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = repository.findByUsername(username);
        
        if (user.isPresent()) {
            var userObj = user.get();
            
            // Debugging roles and authorities
            System.out.println("User Roles: " + userObj.getRoles().toString());
       
            // Convert roles to authorities (with "ROLE_" prefix if necessary)
            var authorities = userObj.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(formatRole(role.getRole()))) // Prefix with "ROLE_"
                .collect(Collectors.toList());

            // Log authorities for debugging
            System.out.println("Authorities: " + authorities);
            System.out.println("Roles: " + userObj.getRole_name());
            
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .authorities(authorities)  // Use authorities instead of roles()
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }

    
    
    
    /*	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = repository.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            var authorities = userObj.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("" + role.getRole()))
                .collect(Collectors.toList());
            
            // Log authorities for debugging
            System.out.println("Authorities: " + authorities);
            System.out.println("o roles: " + userObj.getRole_name());
           // System.out.println("1 roles: " + userObj.getRoles().toString());
            
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(userObj.getRole_name())
                    .authorities(authorities)
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }   

   
*/
    // Helper method to format roles correctly
    private String formatRole(String role) {
        return role.startsWith("ROLE_") ? role : "ROLE_" + role;
    }
	
	
	
}
