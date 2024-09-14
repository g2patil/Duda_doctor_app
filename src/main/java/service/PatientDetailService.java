package service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Patient;
import repository.PatientRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PatientDetailService {

    @Autowired
    private PatientRepository patientRepository;

    // Create a new patient
    public Patient createPatient(Patient patient) {
       patient.setCreatedAt(LocalDateTime.now());
       patient.setUpdatedAt(LocalDateTime.now());
        return patientRepository.save(patient);
    }

    // Get a patient by ID
    public  Optional<Patient> getPatientById(Integer patientId) {
        return patientRepository.findById(patientId);
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Update an existing patient
    @Transactional
    public Optional<Patient> updatePatient(Integer patientId, Patient patientDetails) {
        return patientRepository.findById(patientId)
                .map(patient -> {
                    patient.setFirstName(patientDetails.getFirstName());
                    patient.setLastName(patientDetails.getLastName());
                    patient.setDateOfBirth(patientDetails.getDateOfBirth());
                    patient.setGender(patientDetails.getGender());
                    patient.setContactNumber(patientDetails.getContactNumber());
                    patient.setEmail(patientDetails.getEmail());
                    patient.setAddress(patientDetails.getAddress());
                    patient.setUpdatedAt(LocalDateTime.now());
                    return patientRepository.save(patient);
                });
    }

    // Delete a patient by ID
    public void deletePatient(Integer patientId) {
        patientRepository.deleteById(patientId);
    }

    // Search for patients by last name (example method)
    public List<Patient> findPatientsByLastName(String lastName) {
        return patientRepository.findByLastName(lastName);
    }
    
    public static Specification searchPatients(String firstName, String lastName, String gender, String contactNumber, String email) {
        Specification<Patient> spec = Specification.where(null);
        boolean isAnyParameterProvided = false;
       
            if (firstName != null) {
                spec = spec.and(PatientSpecification.hasFirstName(firstName));
                isAnyParameterProvided = true;
            }
            if (lastName != null) {
                spec = spec.and(PatientSpecification.hasLastName(lastName));
                isAnyParameterProvided = true;
            }
            if (gender != null) {
                spec = spec.and(PatientSpecification.hasGender(gender));
                isAnyParameterProvided = true;
            }
            if (contactNumber != null) {
                spec = spec.and(PatientSpecification.hasContactNumber(contactNumber));
                isAnyParameterProvided = true;
            }
            if (email != null) {
                spec = spec.and(PatientSpecification.hasEmail(email));
                isAnyParameterProvided = true;
            }
           
            if (!isAnyParameterProvided) {
                // If no parameters are provided, either throw an exception or return a Specification that matches all patients
                throw new IllegalArgumentException("At least one search parameter must be provided.");
                // Uncomment the following line if you want to match all patients when no parameters are provided
                // spec = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
            }
            return spec;
        
    }
    
    
}
