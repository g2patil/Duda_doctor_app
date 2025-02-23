package service;

import model.Institute;
import repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class InstituteService {

    @Autowired
    private InstituteRepository instituteRepository;

    public Institute addInstitute(Institute institute) {
        // Set created_at timestamp
      //  institute.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return instituteRepository.save(institute);
    }

    public List<Institute> getAllInstitutes() {
        return instituteRepository.findAll();
    }
}
