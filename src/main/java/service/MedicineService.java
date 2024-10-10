package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Medicine;
import repository.MedicineRepository;

import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    // Service method to search for medicines by name
  /*  public List<Medicine> searchByName(String name) {
        return medicineRepository.findByNameContainingIgnoreCase(name);
    }*/
    
    public List<String> searchMedicineNamesByName(String name) {
        return medicineRepository.findMedicineNamesByNameContainingIgnoreCase(name);
    }
}

