package security;


import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import exception.CustomLogoutException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null && !authentication.getAuthorities().stream()
        	    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"))) {
            throw new CustomLogoutException("Custom error during logout");
        }
        // Additional logout logic if needed
    }
}
