package com.Duda_doctor_app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import model.JwtUtil;
import model.MyUser;
import model.MyUserRepository;
import model.Patient;
import model.bldg;
import model.bldgRepository;
import repository.PatientRepository;


@Controller
@RestController
@RequestMapping("/adnya")
public class ContentController {

	@GetMapping("/home")
	public String handerWelcome() {
		return "Home";
	}
	
	@GetMapping("/admin/home")
	public String handeradminWelcome() {
		return "Admin Home";
	}
	
	@GetMapping("/user/home")
	public String handeruserWelcome() {
		return "User Home";
	}
	@Autowired
	private bldgRepository BldgRepository;
	
	@Autowired
	private MyUserRepository myUserRepository;
	
	
	@Autowired
	private PasswordEncoder  passwordEncoder;
	
	@Autowired
	private
	PatientRepository  patientRepository;
	
	@PostMapping("/register/user")
	public MyUser createUser(@RequestBody MyUser user){
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		//return  // myUserRepository.save(user);
				 try {
			            return myUserRepository.save(user);
			        } catch (DataIntegrityViolationException e) {
			            // Handle the unique constraint violation (e.g., username already exists)
			            throw new RuntimeException("Username already exists");
			        }
}
	
	@PostMapping("/register/bldg")
	public bldg createbldg(@RequestBody bldg Bldg){
		
				return BldgRepository.save(Bldg);
	
}	
	@PostMapping("/register/patient")
	public Patient createPatient(@RequestBody Patient patient){
		
				return patientRepository.save(patient);
	
}		
	
	
	
	
	@GetMapping("/users")
	 public List<MyUser> getUsername() {
		//return null;
	 return myUserRepository.findAll();
    }
	
	@GetMapping("/users/find/{usr}")
	 public Optional<MyUser> getUser(@PathVariable String usr) {
		//return null;
	 return myUserRepository.findByUsername(usr);
   }
	

	
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	private final SecurityContextHolderStrategy 
	securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
	
	
	 @PostMapping("/login")
     public boolean login(@RequestBody AuthRequest authRequest) {
	        try {
	            Authentication authentication = authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            authRequest.getUsername(),
	                            authRequest.getPassword()
	                    )
	            );
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	            SecurityContext context = securityContextHolderStrategy.createEmptyContext();
	            context.setAuthentication(authentication);
	            
	        //    myUserRepository.saveContext(context, authRequest); 
	       
	            return true;
	        } catch (AuthenticationException e) {
	            return false;
	        }
	    }
	
	
	
}
