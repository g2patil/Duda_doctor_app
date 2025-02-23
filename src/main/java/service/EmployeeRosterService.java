package service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.EmployeeRoster;
import repository.EmployeeRosterRepository;

@Service
public class EmployeeRosterService {

    @Autowired
    private EmployeeRosterRepository employeeRosterRepository;

    public EmployeeRoster addEmployeeRoster(EmployeeRoster employeeRoster) {
        return employeeRosterRepository.save(employeeRoster);
    }
    
  

    public List<Object[]> getAllEmployeeRoster() {
        return employeeRosterRepository.findAllEmployeeRoster();
    }
    
    public List<Map<String, Object>> getReservationSummary() {
        return employeeRosterRepository.getReservationSummary();
    }
    
}
