package service;

import dto.TransactionDTO;
import model.*;
import repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private SchoolTransactionRepository repository;

    @Autowired
    private MyUserRepository userRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private AccountMainHeadRepository accountMainHeadRepository;

    @Autowired
    private AccountSubHeadRepository accountSubHeadRepository;

    public void saveTransaction(TransactionDTO dto) {
        SchoolTransaction transaction = new SchoolTransaction();

        transaction.setTransactionDate(dto.getTransactionDate());
        transaction.setAmount(dto.getAmount());
        transaction.setPaymentMode(dto.getPaymentMode());
        transaction.setCash_bank(dto.getCash_bank());
        transaction.setDescription(dto.getDescription());
        transaction.setUpdatedOn(LocalDateTime.now());

        // ✅ Set full entities instead of IDs
        MyUser user = userRepository.findById(dto.getUserId()).orElse(null);
        AccountType accountType = accountTypeRepository.findById(dto.getAccountTypeId()).orElse(null);
        AccountMainHead accountMainHead = accountMainHeadRepository.findById(dto.getAccountMainHeadId()).orElse(null);
        AccountSubHead accountSubHead = accountSubHeadRepository.findById(dto.getAccountSubHeadId()).orElse(null);

       // transaction.setUser(user);
        transaction.setAccountType(accountType);
       // transaction.setAccountMainHead(accountMainHead);
     //   transaction.setAccountSubHead(accountSubHead);

        repository.save(transaction);

        System.out.println("Saving to DB: " + transaction);
    }

	public void saveTransaction(SchoolTransaction transaction) {
		// TODO Auto-generated method stub
		 repository.save(transaction);  // Saves to DB
	}
	
	public SchoolTransaction getLastTransaction() {
	    return repository.findTopByOrderByTransactionDateDesc();
	}
	
	  public List<SchoolTransaction> getTransactionsForToday() {
	        LocalDate today = LocalDate.now(); // Get today's date
	        return repository.findByTransactionDate(today);
	    }

	//  @Autowired
	  //  private SchoolTransactionRepository repository;

	    public List<SchoolTransaction> getLatestFiveTransactionsById() {
	        return repository.findTop5ByOrderByTransactionIdDesc();
	    }
	    
	    public List<Map<String, Object>> getLast5Transactions() {
	        // Fetch the raw data from the repository
	        return repository.findLast5Transactions();
	    }

		public List<Map<String, Object>> findLast5Transactions() {
			// TODO Auto-generated method stub
			 return repository.findLast5Transactions();
		}
		
		  public List<Object[]> getTransactionReport(LocalDate fromDate, LocalDate toDate, Integer accountTypeId) {
		        return repository.fetchTransactionReport(fromDate, toDate, accountTypeId);
		    }
	
}
