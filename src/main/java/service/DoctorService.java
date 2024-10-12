package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.DoctorInfo;
import repository.DoctorRepository;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorInfo findDoctorById(Long doctorId) {
        Optional<DoctorInfo> doctorOptional = doctorRepository.findById(doctorId);
        return doctorOptional.orElse(null); // Return null or throw an exception if not found
    }
}
