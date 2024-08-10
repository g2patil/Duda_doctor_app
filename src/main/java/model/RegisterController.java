package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import repository.PatientRepository;

@RestController
public class RegisterController {
	
	@Autowired
	private MyUserRepository myUserRepository;
	
	@Autowired
	private PasswordEncoder  passwordEncoder;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@PostMapping("/register/user")
	public MyUser createUser(@RequestBody MyUser user){
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return myUserRepository.save(user);
				
		
	}
	@PostMapping("/register/patient")
	public Patient createPatient(@RequestBody Patient patient){
		
				return patientRepository.save(patient);
	
}		
	 
	

}
