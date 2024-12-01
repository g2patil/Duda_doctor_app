package service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import model.ClubUser;
import repository.ClubUserRepository;

@Service
public class ClubUserDetailsService implements UserDetailsService {

    @Autowired
    private ClubUserRepository clubUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ClubUser> user = clubUserRepository.findByUsername(username);

        if (user.isPresent()) {
            ClubUser userObj = user.get();
            
            // Log roles for debugging
            System.out.println("User Roles: " + userObj.getRoles());
            
            // Map roles to authorities
            var authorities = userObj.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(formatRole(role.getRole())))
                .collect(Collectors.toList());

            // Log authorities for debugging
            System.out.println("Authorities: " + authorities);

            // Build UserDetails
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .authorities(authorities)
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }

    // Helper to ensure roles are prefixed
    private String formatRole(String role) {
        return role.startsWith("ROLE_") ? role : "ROLE_" + role;
    }
}
