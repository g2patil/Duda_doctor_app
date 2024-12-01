package service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomUserDetailsService implements UserDetailsService {

    private final UserDetailsService userDetailService;
    private final UserDetailsService clubUserDetailService;

    public CustomUserDetailsService(UserDetailsService userDetailService, UserDetailsService clubUserDetailService) {
        this.userDetailService = userDetailService;
        this.clubUserDetailService = clubUserDetailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to load user details from the first service (userDetailService)
        try {
        	 System.out.println("1--------------> " +username);
                         return userDetailService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e1) {
            // If not found, try the second service (clubUserDetailService)
        	 System.out.println("2--------------> " +username);
            return clubUserDetailService.loadUserByUsername(username);
        }
    }
}
