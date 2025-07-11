package com.Duda_doctor_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.adnya.Valid;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.SubHeadDetailsDTO;
import dto.TransactionDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import model.AccountMainHead;
import model.AccountSubHead;
import model.AccountType;
import model.ClubUser;
import model.DoctorInfo;
import model.EmployeeRoster;
import model.ExamDiffLevel;
import model.ExamMTopic;
import model.ExamQuestion;
import model.ExamSTopic;
import model.Institute;
import model.MaharashtraReservation;
import model.Medicine;
import model.MyUser;
import model.MyUserDetailService;
import model.MyUserRepository;
import model.OPD;
import model.Patient;
import model.QuizAttempt;
import model.Role;
import model.RolePaidStatus;
import model.RoleSubActivity;
import model.School;
import model.SchoolTransaction;
import model.bldg;
import model.bldgRepository;
import model.club_m_activity;
import model.club_s_activity;
import repository.AccountMainHeadRepository;
import repository.AccountSubHeadRepository;
import repository.AccountTypeRepository;
import repository.ClubUserRepository;
import repository.EmployeeRosterRepository;
import repository.ExamDiffLevelRepository;
import repository.ExamMTopicRepository;
import repository.ExamQuestionRepository;
import repository.ExamSTopicRepository;
import repository.InstituteRepository;
import repository.OPDRepository;
import repository.PatientRepository;
import repository.RoleRepository;
import repository.RoleSubActivityRepository;
import repository.SchoolRepository;
import repository.SchoolTransactionRepository;
import repository.club_m_activityRepository;
import repository.club_s_activityRepository;
import service.AccountTypeService;
import service.ClubSActivityService;
import service.ClubUserService;
import service.DoctorService;
import service.EmployeeRosterService;
import service.ExamMTopicService;
import service.ExamQuestionService;
import service.InstituteService;
import service.JwtTokenProvider;
import service.MaharashtraReservationService;
import service.MedicineService;
import service.OPDService;
import service.OTPService;
import service.PatientDetailService;
import service.QuizAttemptRequest;
import service.QuizService;
import service.RoleService;
import service.SchoolService;
import service.TransactionService;


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
	
	@Autowired
	private
	ExamQuestionRepository  examQuestionRepository;
	

    @Autowired
    private ExamMTopicRepository examMTopicRepository;

    @Autowired
    private ExamSTopicRepository examSTopicRepository;

    @Autowired
    private ExamDiffLevelRepository examDiffLevelRepository;
    
    @Autowired
    private QuizService quizService;
	
	 @Autowired
	    private ExamQuestionService examQuestionService;
	 
	 @Autowired
	    private ExamMTopicService examMTopicService;
	 
	 @Autowired
	    private MaharashtraReservationService reservationService;
	// @Autowired
	//	   private RoleService roleService;
	 @Autowired
	    private EmployeeRosterRepository  employeeRosterRepository;
	 
	 @Autowired
	    private SchoolTransactionRepository transactionRepository;

	    @Autowired
	    private AccountTypeRepository accountTypeRepo;

	    @Autowired
	    private AccountMainHeadRepository accountMainHeadRepo;

	    @Autowired
	    private AccountSubHeadRepository accountSubHeadRepo;
	     
	    @Autowired
	    private MyUserRepository userRepo;
	    
	    @Autowired
	    private TransactionService transactionService;
	    
	    @Autowired
	    private AccountTypeService accountTypeService;

	    @Autowired
	    private AccountMainHeadRepository accountMainHeadRepository;
	    
	    @Autowired
	    private AccountSubHeadRepository subHeadRepository;
	    
	    @Autowired
	    private TransactionService service1;
	 
	 /************For School***************/
	    
	    @GetMapping("/account/combine/report")
	    public List<Object[]> getcombTransactionReport(
	            @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
	            @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
	            @RequestParam("accountTypeId") Integer accountTypeId,
	            @RequestParam("accountTypeId1") Integer accountTypeId1
	            ) {

	        return service1.getCombinedTransactions(fromDate, toDate, accountTypeId,accountTypeId1);
	    }
	    
	    
	    
	    
	    @GetMapping("/account/report")
	    public List<Object[]> getTransactionReport(
	            @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
	            @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
	            @RequestParam("accountTypeId") Integer accountTypeId) {

	        return service1.getTransactionReport(fromDate, toDate, accountTypeId);
	    }
	    
	    
	    
	    @GetMapping("/account/sub-head/by-main-head/{mainHeadId}")
	    public ResponseEntity<List<AccountSubHead>> getSubHeadsByMainHead(@PathVariable Long mainHeadId) {
	        List<AccountSubHead> subHeads = subHeadRepository.findByAccountMainHead_AccountMainHeadId(mainHeadId);
	        return ResponseEntity.ok(subHeads);
	    }
	    
	    
	    @GetMapping("/account/sub-head/by-main-head_01/")
	    public ResponseEntity<List<AccountSubHead>> getSubHeadsByMainHeadAndType(
	        @RequestParam Long mainHeadId,
	        @RequestParam Long accountTypeId) {
	        
	        List<AccountSubHead> subHeads = subHeadRepository.findByAccountMainHead_AccountMainHeadIdAndAccountType_AccountTypeId(mainHeadId, accountTypeId);
	        return ResponseEntity.ok(subHeads);
	    }
	    
	    
	    
	    @GetMapping("/account/sub-head-detail")
	    public ResponseEntity<List<Map<String, Object>>> getAccountSubids(
	            @RequestParam Long s  // Expecting format: YYYY-MM-DD
	         // @RequestParam String t     // Expecting format: YYYY-MM-DD
	    ) {
	        return ResponseEntity.ok(accountSubHeadRepo.getAccountSubids( s));
	    }
	    
	    @GetMapping("/account/sub-head-details/{id}")
	    public ResponseEntity<?> getSubHeadDetails(@PathVariable Long id) {
	        Optional<AccountSubHead> subHeadOpt = accountSubHeadRepo.findById(id);
	        if (!subHeadOpt.isPresent()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sub Head not found");
	        }

	        AccountSubHead subHead = subHeadOpt.get();
	        AccountMainHead mainHead = subHead.getAccountMainHead();
	        AccountType accountType = mainHead.getAccountType();

	       SubHeadDetailsDTO dto = new SubHeadDetailsDTO(
	            subHead.getAccountSubHeadId(),
	            mainHead.getAccountMainHeadId(),
	            accountType.getAccountTypeId(),
	            mainHead.getAccountMainHead(),
	            accountType.getAccountType()
	        );

	        return ResponseEntity.ok(dto);
	    }

	    
	    @GetMapping("/school/account/main-head/by-account-type/{accountTypeId}")
	    public List<AccountMainHead> getByAccountTypeId(@PathVariable Long accountTypeId) {
	        return accountMainHeadRepository.findByAccountType_AccountTypeId(accountTypeId);
	    }
	    
	    
	    
	    
	    @GetMapping("/school/account/account-types")
	    public List<AccountType> getAllAccountTypes() {
	        return accountTypeService.getAllAccountTypes();
	    }
	    
	    
	    
	    
	    
	    @GetMapping("/school/account/account-sub-heads")
	    public ResponseEntity<List<Map<String, Object>>> getAllAccountSubHeads() {
	    	 List<AccountSubHead> accountSubHeads = accountSubHeadRepo.findAll();
	    	    
	    	    // Prepare the response by manually creating a Map for each entity
	    	    List<Map<String, Object>> response = new ArrayList<>();

	    	    for (AccountSubHead accountSubHead : accountSubHeads) {
	    	        Map<String, Object> accountSubHeadMap = new HashMap<>();
	    	        
	    	        // Include accountSubHeadId, accountMainHeadId, accountSubHead (name)
	    	        accountSubHeadMap.put("accountSubHeadId", accountSubHead.getAccountSubHeadId());
	    	        accountSubHeadMap.put("accountMainHeadId", accountSubHead.getAccountMainHead().getAccountMainHeadId());
	    	        accountSubHeadMap.put("accountSubHead", accountSubHead.getAccountSubHead());
	    	        
	    	        // Add the accountTypeId (from AccountMainHead -> AccountType)
	    	        Long accountTypeId = accountSubHead.getAccountMainHead().getAccountType().getAccountTypeId();
	    	        accountSubHeadMap.put("accountTypeId", accountTypeId);
	    	        
	    	        response.add(accountSubHeadMap);
	    	    }
	    	    
	    	    return ResponseEntity.ok(response);
	    
	    }
	    
	   
	    
	    
	    @GetMapping("/school/account/transactions/today")
	    public List<Map<String, Object>> getTransactionsForToday() {
	      //  return service1.getTransactionsForToday();
	    	//  return service1.getLatestFiveTransactionsById();
	    	 return service1.findLast5Transactions();
	    }
	    
	    
	    
	    @GetMapping("/school/account/last-txn")
	    public ResponseEntity<SchoolTransaction> getLastTransaction() {
	        SchoolTransaction lastTxn = transactionService.getLastTransaction();
	        return ResponseEntity.ok(lastTxn);
	    }
	    
	    
	    
	    
	    
	    
	     @PostMapping("/school/account/add")
	    public ResponseEntity<String> addTransaction(@RequestBody SchoolTransaction transaction) {
	        // You can log or print the incoming data for debugging
	        System.out.println("Transaction Received: " + transaction);

	        // Pass the entity to a service class and save to DB
	        transactionService.saveTransaction(transaction);
	        return ResponseEntity.ok("Transaction saved successfully!");
	    }
	     
	     @DeleteMapping("/school/account/delete/{id}")
	     public ResponseEntity<String> deleteTransaction(@PathVariable Long id) {
	         boolean deleted = transactionService.deleteTransactionById(id);
	         if (!deleted) {
	             return ResponseEntity.notFound().build();
	         }
	         return ResponseEntity.ok("Transaction deleted successfully!");
	     } 
	

	         @PutMapping("/school/account/update/{id}")
	         public ResponseEntity<String> updateTransaction(@PathVariable Long id, @RequestBody SchoolTransaction updatedTransaction) {
	             SchoolTransaction existingTransaction = transactionService.getTransactionById(id);
	             if (existingTransaction == null) {
	                 return ResponseEntity.notFound().build();
	             }

	             existingTransaction.setTransactionDate(updatedTransaction.getTransactionDate());
	             existingTransaction.setAmount(updatedTransaction.getAmount());
	             existingTransaction.setVoucher_no(updatedTransaction.getVoucher_no());
	             existingTransaction.setLf_no(updatedTransaction.getLf_no());
	             existingTransaction.setPaymentMode(updatedTransaction.getPaymentMode());
	             existingTransaction.setCash_bank(updatedTransaction.getCash_bank());
	             existingTransaction.setDescription(updatedTransaction.getDescription());
	             existingTransaction.setUpdatedOn(LocalDateTime.now()); // optional: use current timestamp

	             // If updating relationships by ID (optional, check logic in service/repository)
	             existingTransaction.setAccountType(updatedTransaction.getAccountType()); // if entire object is passed
	             existingTransaction.setAccountMainHead(updatedTransaction.getAccountMainHead());
	             existingTransaction.setAccountSubHead(updatedTransaction.getAccountSubHead());
	             existingTransaction.setUser(updatedTransaction.getUser());

	             transactionService.saveTransaction(existingTransaction);
	             return ResponseEntity.ok("Transaction updated successfully!");  
	             }
	     
	         @GetMapping("/school/account/transaction/{id}")
	         public ResponseEntity<SchoolTransaction> getTransactionById(@PathVariable Long id) {
	             SchoolTransaction transaction = transactionService.findById(id);
	             if (transaction != null) {
	                 return ResponseEntity.ok(transaction);
	             } else {
	                 return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // return 404 if not found
	             }
	         }
	     
	     
	     
	   /*
	   @PostMapping("/school/account/add")
	    public ResponseEntity<String> addTransaction(@RequestBody TransactionDTO transactionDTO) {
	        // You can log or print the incoming data for debugging
	        System.out.println("Transaction Received: " + transactionDTO);

	        // TODO: Pass this DTO to a service class and save to DB
	        transactionService.saveTransaction(transactionDTO);
	        return ResponseEntity.ok("Transaction saved successfully!");
	    }    
	    */
	    
	/*    
	 @PostMapping("/school/account/add")
	 public SchoolTransaction addTransaction(@RequestBody Map<String, Object> payload) {
	     SchoolTransaction tx = new SchoolTransaction();
	     tx.setTransactionDate(LocalDate.parse((String) payload.get("transactionDate")));
	     tx.setAmount(Double.valueOf(payload.get("amount").toString()));
	     tx.setPaymentMode((String) payload.get("paymentMode"));
	     tx.setDescription((String) payload.get("description"));
	     tx.setUpdatedOn(LocalDateTime.now());

	     Long accountTypeId = Long.valueOf(payload.get("accountTypeId").toString());
	     Long mainHeadId = Long.valueOf(payload.get("accountMainHeadId").toString());
	     Long subHeadId = Long.valueOf(payload.get("accountSubHeadId").toString());
	     Long userId = Long.valueOf(payload.get("userId").toString());

	     tx.setAccountType(accountTypeRepo.findById(accountTypeId).orElse(null));
	     tx.setAccountMainHead(accountMainHeadRepo.findById(mainHeadId).orElse(null));
	     tx.setAccountSubHead(accountSubHeadRepo.findById(subHeadId).orElse(null));
	     tx.setUser(userRepo.findById(userId).orElse(null));

	     return transactionRepository.save(tx);
	 }

	*/ 
	 
	 
	 
	 
	 private ResponseEntity<Resource> downloadFile(String filePath, String mediaType) {
	        try {
	            Path file = Paths.get(filePath);
	            Resource resource = new UrlResource(file.toUri());

	            if (resource.exists() || resource.isReadable()) {
	                return ResponseEntity.ok()
	                        .contentType(MediaType.parseMediaType(mediaType))
	                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
	                        .body(resource);
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        } catch (MalformedURLException e) {
	            return ResponseEntity.internalServerError().build();
	        }
	    }
	 
	 @GetMapping("/EmployeeRoster/instructions")
	    public ResponseEntity<Resource> getInstructions() {
	        return downloadFile("H:\\employee_instructions.pdf", "application/pdf");
	    }
	 
	 @GetMapping("/EmployeeRoster/sample_csv")
	 public ResponseEntity<Resource> getSampleCsv() {
	     String filename = "sample_employee.csv";
	     Path file = Paths.get("H:\\sample_employee.csv");
	     Resource resource;
	     try {
	         resource = new UrlResource(file.toUri());
	     } catch (MalformedURLException e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	     }

	     return ResponseEntity.ok()
	             .contentType(MediaType.parseMediaType("text/csv"))
	             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
	             .body(resource);
	 }

	 
	  @GetMapping("/EmployeeRoster/all")
	    public List<EmployeeRoster> getAllEmployees() {
	        return employeeRosterRepository.findAll();
	    }
	  
	  @GetMapping("/EmployeeRoster/{id}")
	    public ResponseEntity<EmployeeRoster> getEmployeeById1(@PathVariable Long id) {
	        Optional<EmployeeRoster> employee = employeeRosterRepository.findById(id);
	        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @PutMapping("/EmployeeRoster/up/{id}")
	    public ResponseEntity<EmployeeRoster> updateEmployee1(@PathVariable Long id, @RequestBody EmployeeRoster updatedEmployee) {
	        return employeeRosterRepository.findById(id).map(employee -> {
	            employee.setEmployeeName(updatedEmployee.getEmployeeName());
	            employee.setPost(updatedEmployee.getPost());
	            employee.setEducationQualification(updatedEmployee.getEducationQualification());
	            // Set other fields...
	            employeeRosterRepository.save(employee);
	            return ResponseEntity.ok(employee);
	        }).orElseGet(() -> ResponseEntity.notFound().build());
	    }
	  
	  
	  
	  
	 // Update Employee
	    @PutMapping("/EmployeeRoster/update/{id}")
	    public ResponseEntity<EmployeeRoster> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRoster updatedEmployee) {
	        EmployeeRoster updated = employeeRosterService.updateEmployee(id, updatedEmployee);
	        return ResponseEntity.ok(updated);
	    }

	    // Delete Employee
	    @DeleteMapping("/EmployeeRoster/delete/{id}")
	    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
	        employeeRosterService.deleteEmployee(id);
	        return ResponseEntity.ok("Employee with ID " + id + " deleted successfully.");
	    }
	 
	 
	 
	 @GetMapping("/EmployeeRoster/inst/{instituteId}")
	    public ResponseEntity<List<Map<String, Object>>> getEmployeeRosters(@PathVariable Long instituteId) {
	        List<Map<String, Object>> rosterList = employeeRosterService.getAllEmployeeRosters(instituteId);
	        return ResponseEntity.ok(rosterList);
	    }

	 
	 @PostMapping("/EmployeeRoster/csv_upload")
	    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
	        employeeRosterService.uploadCsv(file);
	        return ResponseEntity.ok("File uploaded successfully!");
	    }
	 private EmployeeRoster mapToEmployee(String line) {
		    String[] fields = line.split(","); // Assuming CSV is comma-separated
		    System.out.println("********"+fields[1].trim()+"*****");
		    EmployeeRoster employee = new EmployeeRoster();
		    employee.setEmployeeName(fields[17].trim()); // Adjust index based on CSV structure
		    employee.setDateOfBirth(LocalDate.parse(fields[10].trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		    employee.setReservationCategory(fields[0].trim()); // Adjust as needed
          
		    return employee;
		}
	 /*
	 @PostMapping("/EmployeeRoster/csv_upload1")
	 public ResponseEntity<?> uploadCsv(@RequestParam("file") MultipartFile file) {
	     try (BufferedReader reader = new BufferedReader(
	             new InputStreamReader(file.getInputStream(), "UTF-8"))) {
	    	 System.out.println("*************");
	         List<String> errorMessages = new ArrayList<>();
	         List<EmployeeRoster> employees = new ArrayList<>();

	         reader.lines().skip(1).forEach(line -> {
	        	 System.out.println("*******1******");
	             EmployeeRoster employee = mapToEmployee(line);
	             
	             // Check if the record already exists
	             boolean exists = employeeRosterRepository.existsByEmployeeNameAndDateOfBirthAndReservationCategory(
	                     employee.getEmployeeName(), employee.getDateOfBirth(), employee.getReservationCategory());

	             if (exists) {
	                 errorMessages.add("Duplicate record found: " + employee.getEmployeeName() +
	                         ", Date of Birth: " + employee.getDateOfBirth() +
	                         ", Reservation Category: " + employee.getReservationCategory());
	             } else {
	                 employees.add(employee);
	             }
	         });

	         // Save only non-duplicate employees
	         if (!employees.isEmpty()) {
	             employeeRosterRepository.saveAll(employees);
	         }

	         // Return a JSON response with details
	         if (!errorMessages.isEmpty()) {
	             return ResponseEntity.status(HttpStatus.CONFLICT) // 409 Conflict
	                     .body(Map.of("message", "Some records were skipped due to duplicates", "duplicates", errorMessages));
	         }

	         return ResponseEntity.ok(Map.of("message", "File uploaded successfully!", "savedRecords", employees.size()));

	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                 .body(Map.of("error", "Error processing file", "details", e.getMessage()));
	     }
	 }
*/
	 /*
	 @PostMapping("/EmployeeRoster/csv_upload")
	 public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) throws IOException {
	     try {
	         if (file.isEmpty()) {
	             return ResponseEntity.badRequest().body("File is empty. Please upload a valid CSV file.");
	         }
	         
	         employeeRosterService.uploadCsv(file);
	         return ResponseEntity.ok("File uploaded successfully!");
	     } catch (IllegalArgumentException e) {
	         return ResponseEntity.badRequest().body("Invalid file format: " + e.getMessage());
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
	     }
	 }
	 */
	 
	 
	 @Autowired
	    private InstituteService instituteService;

	 
	 // Add a new institute
	    @PostMapping("/Institute/add")
	    public Institute addInstitute(@RequestBody Institute institute) {
	        return instituteService.addInstitute(institute);
	    }

	    // Get all institutes
	    @GetMapping("/Institute/all")
	    public List<Institute> getAllInstitutes() {
	        return instituteService.getAllInstitutes();
	    }	 
	 
	    @Autowired
	    private SchoolService schoolService;

	    @PostMapping("/School/add")
	    public ResponseEntity<?> addSchool(@RequestBody @Valid School school, BindingResult bindingResult) {
	        // Check for validation errors
	        if (bindingResult.hasErrors()) {
	            // If there are validation errors, return 400 Bad Request
	            String errorMessages = bindingResult.getAllErrors().stream()
	                .map(ObjectError::getDefaultMessage)
	                .collect(Collectors.joining(", "));
	            return ResponseEntity.badRequest().body("Validation failed: " + errorMessages);
	        }

	        try {
	            // Save the school via the service layer
	            School savedSchool = schoolService.addSchool(school);

	            // Return 201 Created response with the saved school data
	            return new ResponseEntity<>(savedSchool, HttpStatus.CREATED);

	        } catch (Exception e) {
	            // Handle unexpected errors (e.g., Institute not found)
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error creating school: " + e.getMessage());
	        }
	    }
	    
	    @Autowired
	    private EmployeeRosterService employeeRosterService;

	    @Autowired
	    private InstituteRepository instituteRepository;

	    @Autowired
	    private SchoolRepository schoolRepository;
	    
	    
	 // Fetch Employee by ID
	    @GetMapping("/EmployeeRoster/fetch/{id}")
	    public ResponseEntity<EmployeeRoster> getEmployeeById(@PathVariable Long id) {
	        Optional<EmployeeRoster> employee = employeeRosterService.getEmployeeById(id);

	        return employee.map(ResponseEntity::ok)
	                       .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	    
	    @GetMapping("/EmployeeRoster/resv_by_date")
	    public ResponseEntity<List<Map<String, Object>>> getReservationByDate(
	            @RequestParam String s  // Expecting format: YYYY-MM-DD
	         // @RequestParam String t     // Expecting format: YYYY-MM-DD
	    ) {
	        return ResponseEntity.ok(employeeRosterService.getReservationByDate( s));
	    }
	    
	    @GetMapping("/EmployeeRoster/goshwara_by_cat")
	    public ResponseEntity<List<Map<String, Object>>> getgoshwaraByCat(
	            @RequestParam Long s , // Expecting format: YYYY-MM-DD
	          @RequestParam String dt     // Expecting format: YYYY-MM-DD
	    ) {
	        return ResponseEntity.ok(employeeRosterService.getgoshwaraByCat( s,dt));
	    }
	    
	    
	    
	    
	    @GetMapping("/EmployeeRoster/resv_per_by_date")
	    public ResponseEntity<List<Map<String, Object>>> getReservationPerByDate(
	            @RequestParam String s  // Expecting format: YYYY-MM-DD
	         // @RequestParam String t     // Expecting format: YYYY-MM-DD
	    ) {
	        return ResponseEntity.ok(employeeRosterService.getReservationPerByDate( s));
	    }
	    
	    
	    
	    
	    @GetMapping("/EmployeeRoster/summary/{instituteId}")
	    public ResponseEntity<List<Map<String, Object>>> getReservationSummary(
	            @PathVariable Long instituteId,
	            @RequestParam String s  // Expecting format: YYYY-MM-DD
	         // @RequestParam String t     // Expecting format: YYYY-MM-DD
	    ) {
	        return ResponseEntity.ok(employeeRosterService.getReservationSummary(instituteId, s));
	    }
	 
/*
	    @GetMapping("/EmployeeRoster/summary/{instituteId}")
	    public ResponseEntity<List<Map<String, Object>>> getReservationSummary(@PathVariable Long instituteId) {
	        return ResponseEntity.ok(employeeRosterService.getReservationSummary(instituteId));
	    }
	    */
	    
	    @PostMapping("/EmployeeRoster/add")
	    public ResponseEntity<EmployeeRoster> addEmployeeRoster(@RequestBody EmployeeRoster employeeRoster) {
	        try {
	            // Fetch Institute and School from their IDs
	            Institute institute = instituteRepository.findById(employeeRoster.getInstitute().getId())
	                    .orElseThrow(() -> new RuntimeException("Institute not found"));
	            School school = schoolRepository.findById(employeeRoster.getSchool().getId())
	                    .orElseThrow(() -> new RuntimeException("School not found"));

	            // Set the Institute and School in the EmployeeRoster
	            employeeRoster.setInstitute(institute);
	            employeeRoster.setSchool(school);
	            employeeRoster.setCreatedAt(LocalDate.now());

	            // Save the EmployeeRoster entity
	            EmployeeRoster savedEmployeeRoster = employeeRosterService.addEmployeeRoster(employeeRoster);

	            return new ResponseEntity<>(savedEmployeeRoster, HttpStatus.CREATED);
	        } catch (Exception e) {
	        	e.printStackTrace(); // Logs the error in the console
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }
	    
	    @Autowired
	    private MaharashtraReservationService service;

	    // Add Category
	    @PostMapping("/resv/add")
	    public ResponseEntity<List<MaharashtraReservation>> addCategories(@RequestBody List<MaharashtraReservation> reservations) {
	        List<MaharashtraReservation> savedReservations = service.addCategories(reservations);
	        return ResponseEntity.ok(savedReservations);
	    }

	    // Get All Categories
	    @GetMapping("/resv/all")
	    public ResponseEntity<List<MaharashtraReservation>> getAllCategories() {
	        List<MaharashtraReservation> categories = service.getAllCategories();
	        return ResponseEntity.ok(categories);
	    }

	    // Get Category by ID
	    @GetMapping("/resv/{id}")
	    public ResponseEntity<Optional<MaharashtraReservation>> getCategoryById(@PathVariable Long id) {
	        Optional<MaharashtraReservation> category = service.getCategoryById(id);
	        return ResponseEntity.ok(category);
	    }

	    // Update Category
	    @PutMapping("/resv/update/{id}")
	    public ResponseEntity<MaharashtraReservation> updateCategory(
	            @PathVariable Long id,
	            @RequestBody MaharashtraReservation updatedReservation) {
	        MaharashtraReservation updatedCategory = service.updateCategory(id, updatedReservation);
	        return ResponseEntity.ok(updatedCategory);
	    }

	    // Get Bindu Name by Reservation Category ID (For Dropdown)
	    @GetMapping("/resv/bindu-name/{id}")
	    public ResponseEntity<List<MaharashtraReservation>> getBinduNameByResvCatId(@PathVariable Long id) {
	        List<MaharashtraReservation> binduNames = service.getBinduNameByResvCatId(id);
	        return ResponseEntity.ok(binduNames);
	    }
	    
	    @GetMapping("/EmployeeRoster/view")
	    public List<Map<String, Object>> getEmployeeRoster() {
	        List<Object[]> resultList = employeeRosterService.getAllEmployeeRoster();
	        
	        // Convert the Object[] to a List of Maps
	        return resultList.stream().map(row -> {
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
	            return map;
	        }).toList();
	    }
	 
/*******For Club App********/
	 
	 @Autowired
	    private OTPService otpService;

	    @Autowired
	    private ClubUserRepository clubUserRepository;
	 
	 @Autowired
	    private ClubUserService clubUserService;
	 
	 @Autowired
	    private 
	 RoleSubActivityRepository RoleSubActivityRepository;
	 

	 @Autowired
	    private club_m_activityRepository club_m_activityRepository;

	    @GetMapping("/club/get_main_activity")
	    public List<club_m_activity> getAllMainActivities() {
	        return club_m_activityRepository.findAll();
	    }

	    @PostMapping("/club/add_main_activity")
	    public ResponseEntity<?> updateMainActivities(@RequestBody List<club_m_activity> mainActivities) {
	        try {
	            for (club_m_activity activity : mainActivities) {
	                // Update each activity
	            	club_m_activityRepository.save(activity);
	            }
	            return ResponseEntity.ok("Main activities updated successfully with descriptions");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating main activities");
	        }
	    }
	 
	    @Autowired
	    private club_s_activityRepository club_s_activityRepository;

	    @GetMapping("/club/get_sub_activity")
	    public List<club_s_activity> getAllSubActivities() {
	        return club_s_activityRepository.findAll();
	    }
	    
	    @Autowired
	    private ClubSActivityService ClubSActivityService;
	   
	    @GetMapping("/club/get_sub_activitybyid/{id}")
	    public ResponseEntity<List<club_s_activity>> getSubActivities(@PathVariable Long id) {
	        List<club_s_activity> activities = ClubSActivityService.getSubActivities(id);
	        return ResponseEntity.ok(activities);
	    }
	    /*public List<club_s_activity> getSubActivities(Long id) {
	        return ClubSActivityService.findByMainActivityId(id);
	    }
	    /* 
	    /*@GetMapping("/club/get_sub_activitybyid")
	    public List<club_s_activity> getSubActivities(@RequestParam Long mainActivityId) {
	        return club_s_activityRepository.findByid(mainActivityId);
	    }*/

		 
	    
	    /*******tmp***********/
	    @Transactional
	    @PostMapping("/club/add_sub_activity")
	    public ResponseEntity<?> addSubActivity1(@RequestBody club_s_activity subActivityDTO) {
	     
	        club_m_activity mainActivity = club_m_activityRepository.findById(subActivityDTO.getId())
	                .orElseThrow();

	        // Create a new sub-activity
	        club_s_activity subActivity = new club_s_activity();
	        subActivity.setName(subActivityDTO.getName());
	        subActivity.setDescription(subActivityDTO.getDescription());
	        subActivity.setClub_m_activity(mainActivity);

	        // Add and save
	        mainActivity.getSubActivities().add(subActivity);
	       // mainActivityRepository.save(mainActivity);

	     
	        club_s_activity savedSubActivity = club_s_activityRepository.save(subActivity);
	        System.out.println("XXXXXXX  = "+subActivityDTO.getRolePaidStatuses());
	        // Step 2: For each RolePaidStatus, create RoleSubActivity
	        for (RolePaidStatus rolePaidStatus : subActivityDTO.getRolePaidStatuses()) {
	            if (rolePaidStatus.getRoleId() == null) {
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                                     .body("Role ID cannot be null.");
	            }

	            Optional<Role> optionalRole = roleRepository.findById(rolePaidStatus.getRoleId());
	            if (optionalRole.isPresent()) {
	                Role role = optionalRole.get();

	                // Create and save RoleSubActivity
	                RoleSubActivity roleSubActivity = new RoleSubActivity();
	                roleSubActivity.setRole(role);
	                roleSubActivity.setSubActivity(savedSubActivity);
	                roleSubActivity.setPaid(rolePaidStatus.isPaid());
	                roleSubActivity.setStartDate(LocalDate.now());

	                RoleSubActivityRepository.save(roleSubActivity);
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                     .body("Role with ID " + rolePaidStatus.getRoleId() + " not found.");
	            }
	        }

	        return ResponseEntity.ok("Sub-activity and Role-SubActivity links created successfully.");
	    }
	    /*********tmp end************/
	    
	    

	    @PostMapping("/club/add_sub_activity1")
	    public ResponseEntity<?> addSubActivity(@RequestBody club_s_activity subActivityDTO) {
	        // Fetch the main activity
	        club_m_activity mainActivity = club_m_activityRepository.findById(subActivityDTO.getId())
	                .orElseThrow();

	        // Create a new sub-activity
	        club_s_activity subActivity = new club_s_activity();
	        subActivity.setName(subActivityDTO.getName());
	        subActivity.setDescription(subActivityDTO.getDescription());
	        subActivity.setClub_m_activity(mainActivity);

	        // Add and save
	        mainActivity.getSubActivities().add(subActivity);
	       // mainActivityRepository.save(mainActivity);

	        /******************/
	        club_s_activity savedSubActivity = club_s_activityRepository.save(subActivity);

	        // Fetch roles to associate with the sub-activity
	        List<Role> roles = roleRepository.findAll(); // Adjust query to filter roles if needed

	        // Update the role_sub_activity table
	        for (Role role : roles) {
	            RoleSubActivity roleSubActivity = new RoleSubActivity();
	            roleSubActivity.setRole(role);
	            roleSubActivity.setSubActivity(savedSubActivity);

	            // Set custom attributes for role_sub_activity
	            roleSubActivity.setPaid(false); // Example: default to free, or use role-specific logic
	            roleSubActivity.setStartDate(LocalDate.now()); // Default start date; adjust logic as needed

	            RoleSubActivityRepository.save(roleSubActivity);
	        }
	        /****************/
	        
	        
	        
	        return ResponseEntity.ok(subActivity);
	    }



	    
	    
	    

	    @PostMapping("/club/add_user")
	    public ResponseEntity<ClubUser> addClubUser(@RequestBody ClubUser clubUser) {
	    	 System.out.println("Received ClubUser: " + clubUser);
	    	 System.out.println("Received ClubUser: {}"+ clubUser);
	    	 
	        	Set<Role> role =  new HashSet<>();
	        	
	        	 role = (Set<Role>) roleRepository.findByRole("ROLE_"+clubUser.getRole_name());
		    		
	        	
	    	/*	if(clubUser.getRole_name().equals("USER"))
	    		  role = (Set<Role>) roleRepository.findByRole("ROLE_USER");
	    		else if(clubUser.getRole_name().equals("ADMIN"))
	    		 role = (Set<Role>) roleRepository.findByRole("ROLE_ADMIN");
	    		else if(clubUser.getRole_name().equals("SUPER"))
	    			 role = (Set<Role>) roleRepository.findByRole("ROLE_SUPER");
	    		else if(clubUser.getRole_name().equals("CLUB_SUPER"))
	    			 role = (Set<Role>) roleRepository.findByRole("ROLE_CLUB_SUPER");
	    		else role = (Set<Role>) roleRepository.findByRole("USER");
	    		*///user = new MyUser();
	    		//user.setMob(user.getMob());
	    		///user.setUsername(user.getUsername());
	    		//user.setPassword(passwordEncoder.encode(user.getPassword()));
	    		clubUser.setRoles(role);
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 clubUser.setPassword(passwordEncoder.encode(clubUser.getPassword()));
	    	try {
	            ClubUser savedUser = clubUserService.addClubUser(clubUser);
	            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    
	    @PostMapping("/club/login")
		public ResponseEntity<?> club_login(@RequestBody AuthRequest authRequest, HttpServletRequest request, HttpServletResponse response) {
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
	    
	 
	    @PostMapping("/club/generate-otp")
	    public ResponseEntity<String> generateOtp(@RequestParam String mobile) {
	        return ResponseEntity.ok(otpService.generateOTP(mobile));
	    }

	    @PostMapping("/club/validate-otp")
	    public ResponseEntity<String> validateOtp(@RequestParam String mobile, @RequestParam String otp) {
	        boolean isValid = otpService.validateOTP(mobile, otp);
	        if (isValid) {
	            return ResponseEntity.ok("OTP validated successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
	        }
	    }

	    @PostMapping("/club/change-password")
	    public ResponseEntity<String> changePassword(
	            @RequestParam String mobile,
	            @RequestParam String otp,
	            @RequestParam String newPassword) {

	        boolean isValid = otpService.validateOTP(mobile, otp);
	        if (!isValid) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
	        }

	        ClubUser user = clubUserRepository.findByMob(mobile)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        user.setPassword(newPassword); // Hash the password using BCrypt
	        user.setOtp(null); // Clear OTP
	        clubUserRepository.save(user);

	        return ResponseEntity.ok("Password changed successfully");
	    }
	    
	    @GetMapping("/club/users/find/{usr}")
		 public Optional<ClubUser> getclubUser(@PathVariable String usr) {
			//return null;
		 return clubUserRepository.findByUsername(usr);
	   }	    
	    
	   

	    @GetMapping("/club/rolelike")
	    public ResponseEntity<List<Role>> searchRolesByRoleName(@RequestParam String name) {
	        List<Role> roles = roleRepository.findByRoleContainingIgnoreCase(name);
	        if (roles.isEmpty()) {
	            return ResponseEntity.noContent().build(); // Return 204 if no roles found
	        }
	        return ResponseEntity.ok(roles); // Return 200 with matching roles
	    }	    
	    
	    
	    
	    
/*******For Club App End********/ 
	 
	 @PostMapping("/quiz/saveAttempt")
	    public ResponseEntity<QuizAttempt> saveAttempt(@RequestBody QuizAttemptRequest request) {
	        QuizAttempt savedAttempt = quizService.saveQuizAttempt(
	                request.getUserId(),
	                request.getTopicId(),
	                request.getTopicName(),
	                request.getScore(),
	                request.getTotalQuestions(),
	                request.getDetails()
	        );
	        return ResponseEntity.ok(savedAttempt);
	    }
	 
	 
	 
	 
	 
	 
	    @GetMapping("/exam/test")
	    public List<ExamQuestion> getRandomQuestions(
	            @RequestParam("mainTopicId") Long mainTopicId,
	            @RequestParam("questionCount") int questionCount) {
	        
	        ExamMTopic mainTopic  = examMTopicRepository.findById(mainTopicId)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid main topic ID: " + mainTopicId));
// fetch or build your ExamMTopic object here by mainTopicId

	        return examQuestionService.getRandomQuestions(mainTopic, questionCount);
	    }
	 

	    
	 @GetMapping("/exam/get_s_topic/{mTopicId}")
	    public ResponseEntity<List<ExamSTopic>> getSTopicsByMTopicId(@PathVariable Long mTopicId) {
	        List<ExamSTopic> sTopics = examSTopicRepository.findBymTopic_mTopicId(mTopicId);
	        // Map to DTO
	        List<ExamSTopic> sTopicDTOs = sTopics.stream()
	        	
	            .map(sTopic -> {
	                ExamSTopic dto = new ExamSTopic();
	                dto.setSTopicId(sTopic.getsTopicId());
	                dto.setSTopicName(sTopic.getSTopicName());
	                return dto;
	            })
	            .collect(Collectors.toList());

	        return ResponseEntity.ok(sTopicDTOs);
	    }
	 
	 @GetMapping("/exam/practise")
	 public ResponseEntity<List<ExamQuestion>> getQuestions(
	            @RequestParam Long mTopicId,@RequestParam Long sTopicId) {
	        
	        List<ExamQuestion> questions = examQuestionService.getQuestionsByTopics(mTopicId,sTopicId);
	        return ResponseEntity.ok(questions);
	    }
	 
	 
	 
	   

	    @GetMapping("/exam/get_m_topic")
	    public ResponseEntity<List<ExamMTopic>> getMTopics() {
	        List<ExamMTopic> mTopics = examMTopicRepository.findAll();
	        List<ExamMTopic> mTopicDTOs = mTopics
	        		.stream()
	        		.distinct()
	        		.filter(mTopic -> mTopic.getSubTopics() != null )
	        		.map(mTopic -> {
	            ExamMTopic dto = new ExamMTopic();
	            dto.setmTopicId(mTopic.getmTopicId());
	            dto.setmTopicName(mTopic.getmTopicName());
	            return dto;
	        })
	           .collect(Collectors.toList());
	           return ResponseEntity.ok(mTopicDTOs);
	       // return ResponseEntity.ok(dto);
	    }
	 
	 
	 
	 
	 
	 @PostMapping("/exam/add_que")
	 public ResponseEntity<?> addQuestions(@RequestBody List<ExamQuestion> questions) {
	     List<ExamQuestion> savedQuestions = new ArrayList<>();
	     List<String> errors = new ArrayList<>();

	     for (ExamQuestion question : questions) {
	         try {
	             // Validate each question here if needed
	             if (question.getQue() == null || question.getQue().isEmpty()) {
	                 errors.add("Question cannot be empty for question: " + question);
	                 continue; // Skip this question if invalid
	             }

	             // Optional: Check if related entities exist (mTopic, sTopic, diffLevel)
	             if (question.getmTopic() == null || question.getsTopic() == null || question.getDiffLevel() == null) {
	                 errors.add("mTopic, sTopic, or diffLevel cannot be null for question: " + question);
	                 continue;
	             }

	             // Save the question to the database
	             ExamQuestion savedQuestion = examQuestionRepository.save(question);
	             savedQuestions.add(savedQuestion);
	         } catch (Exception e) {
	             errors.add("Failed to save question: " + question + ". Error: " + e.getMessage());
	         }
	     }

	     if (!errors.isEmpty()) {
	         // Return a bad request with error details
	         return ResponseEntity.badRequest().body(errors);
	     }

	     // Return a success response with saved questions
	     return ResponseEntity.ok(savedQuestions);
	 }

	 
	 
	
	@PostMapping("/exam/add_que1")
	//@PostMapping("/createQuestion")
	public ResponseEntity<?> createQuestion(@RequestBody ExamQuestion que_d) {
	    
		 System.out.println("Received Question Data:");
		// System.out.println("Received ExamQuestion: " + que_d.getmTopic().getmTopicName());
	        System.out.println("Question ID: " + que_d.getQuestionId());
	        System.out.println("Question: " + que_d.getQue());
	        System.out.println("Answer A: " + que_d.getAnsA());
	        System.out.println("Answer B: " + que_d.getAnsB());
	        System.out.println("Answer C: " + que_d.getAnsC());
	        System.out.println("Answer D: " + que_d.getAnsD());
	        System.out.println("Correct Answer: " + que_d.getCorrectAnswer());
	        System.out.println("Explanation: " + que_d.getExpln());
	        
	       ExamMTopic mTopic = examMTopicRepository.findById(que_d.getmTopic().getmTopicId())
	                .orElseThrow(() -> new RuntimeException("Invalid mTopic ID"));
	        ExamSTopic sTopic = examSTopicRepository.findById(que_d.getsTopic().getsTopicId())
	                .orElseThrow(() -> new RuntimeException("Invalid mTopic ID"));
	        ExamDiffLevel diffLevel = examDiffLevelRepository.findById(que_d.getDiffLevel().getDiffLevelId())
	        	    .orElseThrow(() -> new RuntimeException("Invalid Difficulty Level ID: "));
	       // examQuestionRepository.save(que_d);
	        
	        System.out.println("mTopic " + mTopic.getmTopicId());
	        System.out.println("sTopic " + sTopic.getsTopicId());
	        System.out.println("diffLevel " + que_d.getDiffLevel().getDiffLevelId());
		
	        ExamQuestion examQuestion = new ExamQuestion();
	        examQuestion.setmTopic(mTopic);
		    examQuestion.setsTopic(sTopic);
		    examQuestion.setDiffLevel(diffLevel);
		    examQuestion.setQue(que_d.getQue());
		    examQuestion.setAnsA(que_d.getAnsA());
		    examQuestion.setAnsB(que_d.getAnsB());
		    examQuestion.setAnsC(que_d.getAnsC());
		    examQuestion.setAnsD(que_d.getAnsD());
		    examQuestion.setCorrectAnswer(que_d.getCorrectAnswer());
		    examQuestion.setExpln(que_d.getExpln());
		    examQuestionRepository.save(examQuestion);
	        
	//	ExamQuestion examQuestion = new ExamQuestion();
	     //   examQuestionRepository.save(examQuestion);

		    return ResponseEntity.ok("Question created successfully");
	        
	        
	        
	        
	        

	    /* Assuming you have a method to fetch mTopic, sTopic, and diffLevel
	    ExamMTopic mTopic = examMTopicRepository.findById(Long.parseLong(questionDto.getSTopic().toString())).orElse(null);
	    ExamSTopic sTopic = examSTopicRepository.findById(questionDto.getSTopic()).orElse(null);
	    ExamDiffLevel diffLevel = examDiffLevelRepository.findById(questionDto.getDiffLevel()).orElse(null);

	    if (mTopic == null || sTopic == null || diffLevel == null) {
	        return ResponseEntity.badRequest().body("Invalid topic or difficulty level ID");
	    }

	    examQuestion.setMTopic(mTopic);
	    examQuestion.setSTopic(sTopic);
	    examQuestion.setDiffLevel(diffLevel);
	    examQuestion.setQue(questionDto.getQue());
	    examQuestion.setAnsA(questionDto.getAnsA());
	    examQuestion.setAnsB(questionDto.getAnsB());
	    examQuestion.setAnsC(questionDto.getAnsC());
	    examQuestion.setAnsD(questionDto.getAnsD());
	    examQuestion.setCorrectAnswer(questionDto.getCorrectAnswer());
	    examQuestion.setExpln(questionDto.getExpln());
         */
	    // Save the question
	    
	}

  /*  public void createQuestion(@RequestBody ExamQuestion examQuestion) {
		ExamSTopic sTopic = examSTopicRepository.findById(1L)
		        .orElseThrow();
		    ExamMTopic mTopic = examMTopicRepository.findById(1L)
			        .orElseThrow();
		    ExamDiffLevel DiffLevel=examDiffLevelRepository.findById(1L)
			        .orElseThrow();
		    ExamQuestion question1 = new ExamQuestion();
		   // question1.setSTopic(Long.parseLong(sTopic.getSTopicId().toString()));
		  //  question1.setMTopic(Long.parseLong(mTopic.getMTopicId().toString()));
		   // question1.setDiffLevel(Long.parseLong(DiffLevel.getDiffLevelId().toString()));
		    
		       System.out.println("\n\n\n"
		       		+ "Received request with "
		       		+ "MTopic: {}"+ examQuestion.getMTopic().toString());
		       System.out.println("\n\n\n");
		       examQuestionService.addQuestion(examQuestion);
    }*/
	
	/*@PostMapping("/exam/add_que")
	public ResponseEntity<?> addQuestion(@RequestBody ExamQuestion question) {
	    ExamSTopic sTopic = examSTopicRepository.findById(1L)
	        .orElseThrow();
	    ExamMTopic mTopic = examMTopicRepository.findById(1L)
		        .orElseThrow();
	    ExamDiffLevel DiffLevel=examDiffLevelRepository.findById(1L)
		        .orElseThrow();
	    
	    ExamQuestion question1 = new ExamQuestion();
	    question1.setSTopic(sTopic.getSTopicId());
	    question1.
	    question1.setMTopic(mTopic.getMTopicId());
	    question1.setDiffLevel(DiffLevel.getDiffLevelId());
	    // Set other properties from questionDto
	    System.out.println("------------"+sTopic.getSTopicId());
	    examQuestionService.addQuestion(question1);
	    return ResponseEntity.ok("Question created successfully");
	}*/
	
	
	
	
	
	
	
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
    
    
    @Autowired
    private MyUserRepository userserv;

    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "User is not authenticated"));
        }

        String username = authentication.getName();
        Optional<MyUser> optionalUser = userserv.findByUsername(username);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
        }

        MyUser user = optionalUser.get();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
        }

        return ResponseEntity.ok(Map.of(
                "userId", user.getId(),
                "username", user.getUsername(),
                "instituteId", user.getInstitute_id(),
                "schoolId", user.getSchool_id()//,
             //   "roleName", user.,
              //  "roles", user.getRoles().stream().map(Role::getName).toList()
        ));
    }
    
    
    
    
	
	private MyUser MyUser(String username) {
		// TODO Auto-generated method stub
		return null;
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
	        authRequest.getInstituteId();
	        
	        System.out.println(">>>>>>>>> " + authRequest.getInstituteId());
	        
	      //  response.put("InstituteId", authRequest.getInstituteId());
         //   response.put("instituteName", authRequest.getInstitute().getName());
	        
	        
	        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
	        
	        //System.out.println("SchoolId: " + SchoolId);
	      //  System.out.println("InstituteId: " + InstituteId);
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
	
	//@Autowired
	//private JwtTokenProvider jwttp;
	
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
