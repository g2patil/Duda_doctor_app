package com.Duda_doctor_app;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
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

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import model.DoctorInfo;
import model.Medicine;
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
import service.DoctorService;
import service.JwtTokenProvider;
import service.MedicineService;
import service.OPDService;
import service.PatientDetailService;


@Controller
@RestController
@RequestMapping("/adnya")
public class ContentController {
	
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
	
	@Autowired
	private OPDService oPDService;
	
	@GetMapping("/opd/history1/{patientId}")
	public ResponseEntity<List<OPD>> getPatientOpdHistory(@PathVariable Integer patientId) {
	    List<OPD> opdHistory = opdRepository.findByPatientId(patientId);
	    if (opdHistory.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(opdHistory);
	}
	
	@GetMapping("/opd/history/{patientId}/{doctorId}")
	public List<OPD> getOPDRecords(
	        @PathVariable int patientId, 
	        @PathVariable int doctorId) {
	    return oPDService.getOPDRecords(patientId, doctorId);
	}
	
	@Autowired
    private DoctorService doctorService;

    @GetMapping("/search/doctor")
    public DoctorInfo searchDoctor(@RequestParam("doctor_id") Long doctorId) {
        return doctorService.findDoctorById(doctorId);
    }


	    @Autowired
	    private MedicineService medicineService;

	    // GET request to search for medicines by name
	  /*  @GetMapping("/med/search")
	    public ResponseEntity<List<Medicine>> searchMedicinesByName(@RequestParam("name") String name) {
	        List<Medicine> medicines = medicineService.searchByName(name);
	        return ResponseEntity.ok(medicines);
	    }*/
	    
	    @GetMapping("/med/search")
	    public ResponseEntity<List<String>> searchMedicinesByName(@RequestParam("name") String name) {
	        List<String> medicineNames = medicineService.searchMedicineNamesByName(name);
	        return ResponseEntity.ok(medicineNames);
	    }
	
	
    @GetMapping("/opd/search")
    public ResponseEntity<List<OPD>> searchOpdByVisitDate(
    		@RequestParam("doctorid") int doctorid,
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate) {

        // Convert string dates to LocalDate
        LocalDate startDate = LocalDate.parse(fromDate);
        LocalDate endDate = LocalDate.parse(toDate);
        List<OPD> opdList =opdRepository.findBydoctoridAndVisitDateBetween(doctorid,startDate, endDate);

        // Query OPD records by date range
        //List<OPD> opdList = opdRepository.findByVisitDateBetween(startDate, endDate);

        return ResponseEntity.ok(opdList);
    }
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest, HttpServletRequest request, HttpServletResponse response) {
	    HttpSession session = request.getSession(true); // Ensure session is created if not exists

	    try {
	    	//  Cookie cookie = new Cookie("JSESSIONID",null);
	        System.out.println("Session ID: " + session.getId());

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        authRequest.getUsername(),
	                        authRequest.getPassword()
	                )
	        );

	        // Set authentication in the security context
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String sessionId = session.getId();
	        
	        
	        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

 
	        System.out.println("Session ID after authentication: " + sessionId);

	        return ResponseEntity.ok().body(Map.of("sessionId", sessionId, "message", "Login successful"));
	    } catch (AuthenticationException e) {
	    	System.out.println("Authentication failed"+e);
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                             .body(Map.of("error", "Invalid username or password"));
	    }
	}


	
	

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

	
	
	 @GetMapping("/logout")
	    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        // Invalidate the session
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate();
	        }
	        session=null;
	        // Clear cookies
	        Cookie cookie = new Cookie("JSESSIONID", "");
	        cookie.setMaxAge(0);
	        cookie.setPath("/");
	        response.addCookie(cookie);
	        
	        // Optionally, redirect to the login page
	        response.setStatus(HttpServletResponse.SC_OK);
	        response.sendRedirect("/adnya/login?logout");
	    }
	
	
	
	@GetMapping("/test/roles")
    public Map<String, Object> getRoles(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            response.put("roles", authorities.stream()
                                             .map(GrantedAuthority::getAuthority)
                                             .collect(Collectors.toList()));
        } else {
            response.put("roles", "No roles found");
        }
        return response;
    }
	
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
		else role = (Set<Role>) roleRepository.findByRole("USER");
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
	/*@PostMapping("/register/opd")
	public OPD createOpd(@RequestBody OPD opd){
		
				return opdRepository.save(opd);
	
}	*/
	 @Autowired
	    private OPDService opdService;
	@PostMapping("/register/opd")
    public ResponseEntity<OPD> createOpd(@RequestBody OPD opd) {
        OPD createdOpd = opdService.createOpdWithPrescriptions(opd);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOpd);
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
	 //@CrossOrigin(origins = "http://192.168.1.114:8081")
	  @GetMapping("/patient/search1")
	    public List<Patient> searchPatients1(
	            @RequestParam(required = false) String firstName,
	            @RequestParam(required = false) String lastName,
	            @RequestParam(required = false) String gender,
	            @RequestParam(required = false) String contactNumber,
	            @RequestParam(required = false) String email
	    ) {
	       Specification sp= PatientDetailService.searchPatients(firstName, lastName, gender, contactNumber, email);
		   return patientRepository.findAll(sp);
	      // List<Patient> patients = patientRepository.findAll(sp);
	      // return ResponseEntity.ok(patients); // Ensure JSON response
	  }
	
	  @GetMapping("/patient/search")
	    public ResponseEntity<List<Patient>> searchPatients(
	            @RequestParam(required = false) String firstName,
	            @RequestParam(required = false) String lastName,
	            @RequestParam(required = false) String gender,
	            @RequestParam(required = false) String contactNumber,
	            @RequestParam(required = false) String email
	    ) {
	        Specification<Patient> spec = PatientDetailService.searchPatients(firstName, lastName, gender, contactNumber, email);
	        List<Patient> patients = patientRepository.findAll(spec);
	        return ResponseEntity.ok(patients); // Ensure JSON response
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

	
	
	
	
}
