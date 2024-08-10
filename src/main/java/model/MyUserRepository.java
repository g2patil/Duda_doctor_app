package model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContext;

import com.Duda_doctor_app.AuthRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface MyUserRepository extends JpaRepository<MyUser , Long> {
	
 Optional<MyUser> findByUsername(String username);



}
