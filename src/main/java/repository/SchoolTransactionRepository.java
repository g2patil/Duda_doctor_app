package repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.SchoolTransaction;

public interface SchoolTransactionRepository extends JpaRepository<SchoolTransaction, Long> {

	SchoolTransaction findTopByOrderByTransactionDateDesc();
	
	
	List<SchoolTransaction> findByTransactionDate(LocalDate transactionDate);

	//Optional<SchoolTransaction> findTopByUserIdOrderByIdDesc(Long userId);

	List<SchoolTransaction> findTop5ByOrderByTransactionIdDesc();
	
	@Query(
	        value = "SELECT a.transaction_date, c.account_main_head, b.account_sub_head, " +
	                "a.transaction_id, a.amount, a.cash_bank, a.description " +
	                "FROM public.school_transaction a " +
	                "LEFT JOIN public.account_sub_head b ON " +
	                "a.account_type_id = b.account_type_id " +
	                "AND a.account_main_head = b.account_main_head_id " +
	                "AND a.account_sub_head_id = b.account_sub_head_id " +
	                "LEFT JOIN public.account_main_head c ON " +
	                "a.account_type_id = c.account_type_id " +
	                "AND a.account_main_head = c.account_main_head_id " +
	                "ORDER BY a.transaction_id DESC LIMIT 5",
	        nativeQuery = true
	    )
	  //  List<Object[]> findLast5Transactions();
	List<Map<String, Object>> findLast5Transactions();
	

	    @Query(value = "SELECT a.transaction_date, c.account_main_head, b.account_sub_head, " +
	                   "a.transaction_id, a.cash_bank, a.description, " +
	                   "CASE WHEN a.cash_bank = 'Cash' THEN a.amount ELSE 0 END AS cash_amt, " +
	                   "CASE WHEN a.cash_bank = 'Bank' THEN a.amount ELSE 0 END AS bank_amt " +
	            "FROM public.school_transaction a " +
	            "LEFT JOIN public.account_sub_head b ON " +
	                "a.account_type_id = b.account_type_id " +
	                "AND a.account_main_head = b.account_main_head_id " +
	                "AND a.account_sub_head_id = b.account_sub_head_id " +
	            "LEFT JOIN public.account_main_head c ON " +
	                "a.account_type_id = c.account_type_id " +
	                "AND a.account_main_head = c.account_main_head_id " +
	            "WHERE a.account_type_id = :accountTypeId " +
	            "AND a.transaction_date BETWEEN :fromDate AND :toDate " +
	            "ORDER BY a.transaction_date, a.transaction_id", 
	        nativeQuery = true)
	    List<Object[]> fetchTransactionReport(@Param("fromDate") LocalDate fromDate,
	                                          @Param("toDate") LocalDate toDate,
	                                          @Param("accountTypeId") Integer accountTypeId);


	
	
}
