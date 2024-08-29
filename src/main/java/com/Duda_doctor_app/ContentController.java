package com.Duda_doctor_app;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import model.MyUser;
import model.MyUserDetailService;
import model.MyUserRepository;
import model.OPD;
import model.Patient;
import model.Role;
import model.bldg;
import model.bldgRepository;
import repository.OPDRepository;
import repository.PatientRepository;
import repository.RoleRepository;
import service.JwtTokenProvider;
import service.PatientDetailService;


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
	private OPDRepository opdRepository;
	
	
	@Autowired
	private PasswordEncoder  passwordEncoder;
	
	@Autowired
	private
	PatientRepository  patientRepository;
	
	@Autowired
	private
	MyUserDetailService  userService;
	
	@Autowired
	private
	RoleRepository  roleRepository;
	

	
	@PostMapping("/register/user")
	 @Transactional
	public MyUser createUser(@RequestBody MyUser user){
		
		//user.setPassword(passwordEncoder.encode(user.getPassword()));
		// Role roles = roleRepository.findByName(user.getUsername())
        //         .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
      
		//return   myUserRepository.save(user);
		System.out.println("*******"+user.getRole_name()+"&&&");
		System.out.println("*******"+passwordEncoder.encode(user.getPassword()) +"&&&");
		System.out.println("*******"+user.getPassword()+"&&&");
		Set<Role> role =  new HashSet<>();
		if(user.getRole_name().equals("USER"))
		  role = (Set<Role>) roleRepository.findByRole("ROLE_USER");
		else if(user.getRole_name().equals("ADMIN"))
		 role = (Set<Role>) roleRepository.findByRole("ROLE_ADMIN");
		else if(user.getRole_name().equals("SUPER"))
			 role = (Set<Role>) roleRepository.findByRole("ROLE_SUPER");
		else role = (Set<Role>) roleRepository.findByRole("ROLE_USER");
		//user = new MyUser();
		user.setMob(user.getMob());
		user.setUsername(user.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(role);
		
		
		
		
		
		
				 try {
					   
					 // MyUser createdUser = userService.createUser(user, roles);
				      //   ResponseEntity.ok(createdUser);
			            return myUserRepository.save(user);
			         //   roleRepository.add;
			        } catch (DataIntegrityViolationException e) {
			            // Handle the unique constraint violation (e.g., username already exists)
			            throw new RuntimeException("Username already exists");
			        }
				 //user.setRoles(role);
}
	
	@PostMapping("/register/bldg")
	public bldg createbldg(@RequestBody bldg Bldg){
		
				return BldgRepository.save(Bldg);
	
}	
	@PostMapping("/register/patient")
	public Patient createPatient(@RequestBody Patient patient){
		
				return patientRepository.save(patient);
	
}		
	@PostMapping("/register/opd")
	public OPD createOpd(@RequestBody OPD opd){
		
				return opdRepository.save(opd);
	
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
	
	  @GetMapping("/patient/search")
	    public List<Patient> searchPatients(
	            @RequestParam(required = false) String firstName,
	            @RequestParam(required = false) String lastName,
	            @RequestParam(required = false) String gender,
	            @RequestParam(required = false) String contactNumber,
	            @RequestParam(required = false) String email
	    ) {
	       Specification sp= PatientDetailService.searchPatients(firstName, lastName, gender, contactNumber, email);
		  return patientRepository.findAll(sp);
	  }
	
	
	
	
	
	@GetMapping("/patient/find/{pst}")
	 public Optional<Patient> getPatient(@PathVariable Integer pst) {
		//return null;
	 return patientRepository.findByPatientId(pst);
  }
	
	@Autowired
	private JwtTokenProvider jwttp;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	private final SecurityContextHolderStrategy 
	securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
	
	
	
	/*
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
	    try {
	        // Authenticate the user
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        authRequest.getUsername(),
	                        authRequest.getPassword()
	                )
	        );

	        // Set the authentication in the context
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        // Generate JWT token
	        String token = jwttp.generateToken(authentication);

	        // Return the token in the response
	        return ResponseEntity.ok(new AuthResponse(token));

	    } catch (AuthenticationException e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	    }
	}

	*/
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
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
	        
	        // myUserRepository.saveContext(context, authRequest);

	        // Generate JWT token
	     //   String token =jwttp.generateToken(authentication);
			

	        // Return token in the response
		//	System.out.println("bbbbbbbbbbb_____"+token);
	       // return ResponseEntity.ok().body(Map.of("token", token, "message", "Login successful"));

	        
	        // If login is successful, return a JSON response
	       return ResponseEntity.ok().body(Map.of("message", "Login successful"));
	    } catch (AuthenticationException e) {
	    	e.printStackTrace();
	        // If login fails, return a JSON response with an error message
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                             .body(Map.of("error", "Invalid username or password"));
	    }
	}

	
	
	
	
}
