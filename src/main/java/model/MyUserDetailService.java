package model;


import java.util.List;
import java.util.Optional;
import java.util.Set;
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
	
	
	 // Convert Set<Role> to a collection of SimpleGrantedAuthority
    private Set<SimpleGrantedAuthority> getAuthorities(MyUser user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))  // Assuming Role entity has a getRole() method
                .collect(Collectors.toSet());
    }
	
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = repository.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .authorities(userObj.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(formatRole(role.getRole())))
                        .collect(Collectors.toList()))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    // Helper method to format roles correctly
    private String formatRole(String role) {
        return role.startsWith("ROLE_") ? role : "ROLE_" + role;
    }    
	
/*	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<MyUser> user=repository.findByUsername(username);
		if(user.isPresent())
		{
			var userObj=user.get();  
			return  User.builder()
					.username(userObj.getUsername())
					.password(userObj.getPassword())
					//.roles(get_Roles(userObj.))
					.build();
		
		}
		else {
			throw new UsernameNotFoundException(username);
		}
		//return null;
	
	}
	*/
	  private String[] get_Roles(MyUser user) {
	        if (user.getRoles() == null || user.getRoles().isEmpty()) {
	            return new String[]{"USER"}; // Default role
	        }
	        // Convert Set<Role> to String array of role names
	        return user.getRoles().stream()
	                .map(Role::getRole) // Assuming Role entity has a getName() method
	                .toArray(String[]::new);
	    }
	
	
	
}
