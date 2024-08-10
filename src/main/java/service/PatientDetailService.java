package service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Patient;
import repository.PatientRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientDetailService {

    @Autowired
    private PatientRepository patientRepository;

    // Create a new patient
    public Patient createPatient(Patient patient) {
      //  patient.setCreatedAt(LocalDateTime.now());
      //  patient.setUpdatedAt(LocalDateTime.now());
        return patientRepository.save(patient);
    }

    // Get a patient by ID
    public Optional<Patient> getPatientById(UUID patientId) {
        return patientRepository.findById(patientId);
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Update an existing patient
    @Transactional
    public Optional<Patient> updatePatient(UUID patientId, Patient patientDetails) {
        return patientRepository.findById(patientId)
                .map(patient -> {
                    patient.setFirstName(patientDetails.getFirstName());
                    patient.setLastName(patientDetails.getLastName());
                /*    patient.setDateOfBirth(patientDetails.getDateOfBirth());
                    patient.setGender(patientDetails.getGender());
                    patient.setContactNumber(patientDetails.getContactNumber());
                    patient.setEmail(patientDetails.getEmail());
                    patient.setAddress(patientDetails.getAddress());
                    patient.setUpdatedAt(LocalDateTime.now());*/
                    return patientRepository.save(patient);
                });
    }

    // Delete a patient by ID
    public void deletePatient(UUID patientId) {
        patientRepository.deleteById(patientId);
    }

    // Search for patients by last name (example method)
    public List<Patient> findPatientsByLastName(String lastName) {
        return patientRepository.findByLastName(lastName);
    }
}
