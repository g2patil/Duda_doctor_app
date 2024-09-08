package repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    
   // Custom query method to find patients by last name
    List<Patient> findByLastName(String lastName);
    
    // Custom query method to find patients by first and last name
    List<Patient> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Patient> findByPatientId(int patientId);

	List<Patient> findAll(Specification<Patient> spec);
	;

	

	
    
    // Custom query method to find patients by contact number
  //  Optional<Patient> findByContactNumber(String contactNumber);

}
