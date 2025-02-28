package service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Duda_doctor_app.AuthRequest;

import model.EmployeeRoster;
import model.Institute;
import model.MaharashtraReservation;
import model.MyUser;
import model.MyUserDetailService;
import model.MyUserRepository;
import model.School;
import repository.EmployeeRosterRepository;
import repository.MaharashtraReservationRepository;

@Service
public class EmployeeRosterService {

    @Autowired
    private EmployeeRosterRepository employeeRosterRepository;
    
    @Autowired
    private MaharashtraReservationRepository reservationRepository;
    
    @Autowired
	private MyUserDetailService myUserDetailService;

    public EmployeeRoster addEmployeeRoster(EmployeeRoster employeeRoster) {
        return employeeRosterRepository.save(employeeRoster);
    }
    
  

    public List<Object[]> getAllEmployeeRoster() {
        return employeeRosterRepository.getAllEmployeeRoster();
    }
    
    
    /*****************/
    public List<Map<String, Object>> getAllEmployeeRosters(Long instituteId) {
        List<Object[]> results = employeeRosterRepository.findAllEmployeeRoster(instituteId);

        List<Map<String, Object>> rosterList = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("binduId", row[0]);
            map.put("binduName", row[1]);
            map.put("employeeName", row[2]);
            map.put("reservationCategory", row[3]);
            map.put("binduNameMar", row[4]);
            map.put("dateOfPromotion", row[5]);
            map.put("dateOfAppointment", row[6]);
            map.put("dateOfBirth", row[7]);
            map.put("dateOfRetirement", row[8]);
            map.put("casteCertificateNumber", row[9]);
            map.put("casteCertificateDate", row[10]);
            map.put("casteCertificateIssuingAuthority", row[11]);
            map.put("casteValidityCertificateNumber", row[12]);
            map.put("casteValidityCertificateDate", row[13]);
            map.put("comments", row[14]);

            rosterList.add(map);
        }
        return rosterList;
    }
    
    
    /*********************/
    public List<Map<String, Object>> getReservationSummary(Long instituteId) {
        return employeeRosterRepository.getReservationSummary(instituteId);
    }
    
    public void uploadCsv(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(
        		new InputStreamReader(file.getInputStream(), "UTF-8"))) {

            List<EmployeeRoster> employees = reader.lines()
                    .skip(1) // Skip header
                    .map(this::mapToEmployee)
                    .collect(Collectors.toList());

            employeeRosterRepository.saveAll(employees);
        } catch (Exception e) {
            throw new RuntimeException("Error processing file: " + e.getMessage());
        }
    }

    private EmployeeRoster mapToEmployee(String line) {
        String[] fields = line.split(","); // Using tab as a delimiter based on your data structure
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("CSV Row Data: " + Arrays.toString(fields));  // Debugging

        EmployeeRoster employee = new EmployeeRoster();

      

        employee.setBindu_Code(fields[0]);
        employee.setBindu_Id(Long.parseLong(fields[1]));
        employee.setCasteCertificateDate(fields[2].isEmpty() ? null : LocalDate.parse(fields[2], formatter));
        employee.setCasteCertificateIssuingAuthority(fields[3]);
        employee.setCasteCertificateNumber(fields[4]);
        employee.setCasteValidityCertificateDate(fields[5].isEmpty() ? null : LocalDate.parse(fields[5], formatter));
        employee.setCasteValidityCertificateNumber(fields[6]);
        employee.setComments(fields[7]);
        employee.setCommitteeDetailsForCasteValidityCertificate(fields[8]);
        employee.setDateOfAppointment(fields[9].isEmpty() ? null : LocalDate.parse(fields[9], formatter));
        employee.setDateOfBirth(fields[10].isEmpty() ? null : LocalDate.parse(fields[10], formatter));
        employee.setDateOfJoining(fields[11].isEmpty() ? null : LocalDate.parse(fields[11], formatter));
        employee.setDateOfPromotion(fields[12].isEmpty() ? null : LocalDate.parse(fields[12], formatter));
        employee.setDateOfPromotionAppointment(fields[13].isEmpty() ? null : LocalDate.parse(fields[13], formatter));
        employee.setDateOfRetirement(fields[14].isEmpty() ? null : LocalDate.parse(fields[14], formatter));
        employee.setEducationQualification(fields[15]);
        employee.setEmployeeCast(fields[16]);
        employee.setEmployeeName(fields[17]);
        employee.setOtherQualification(fields[18]);
        employee.setPost(fields[19]);
        employee.setProf_Qualification(fields[20]);
        employee.setSevarth_Id(fields[21]);
        employee.setIsActive(true);
        
       Long binduCode = Long.parseLong(fields[0]);  // Assuming this column contains resvCatId
        List<MaharashtraReservation> reservations = reservationRepository.findByResvCatId(binduCode);

        if (!reservations.isEmpty()) {
            MaharashtraReservation reservation = reservations.get(0); // Assuming one match per ID
            employee.setBindu_Name(reservation.getReservationCategory());
            employee.setReservationCategory(""+binduCode);
        }
        
        
      //  employee.setUpdatedAt(fields[27].isEmpty() ? null : LocalDate.parse(fields[27].replace("\"", ""), formatter));
      //  employee.setUpdatedBy(fields[28]);
     //   employee.setBindu_Name(fields[2]);
    //    employee.setCreatedAt(fields[10].isEmpty() ? null : LocalDate.parse(fields[10].replace("\"", ""), formatter));
        //employee.setCreatedBy("G2");
        
        myUserDetailService.getCurrentUser().ifPresent(user -> employee.setCreatedBy(user.getUsername()));

       
        
    //    employee.setReservationCategory(fields[25]);
        Institute institute = new Institute();
        institute.setId(1L);  // Assuming 1 is a valid ID
        employee.setInstitute(institute);

        School school = new School();
        school.setId(1L);
        employee.setSchool(school);      
        
        return employee;
    }

    
}
