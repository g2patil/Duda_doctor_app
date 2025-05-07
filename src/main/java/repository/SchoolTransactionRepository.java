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

	    @Query(
	    		  value = "SELECT row_num,voucher_no,lf_no,transaction_date, account_main_head, account_sub_head,\r\n"
	    		  		+ "	                transaction_id,cash_bank, description,\r\n"
	    		  		+ "					COALESCE(Cash_amt,0) Cash_amt,\r\n"
	    		  		+ "					COALESCE(Bank_amt,0) Bank_amt,\r\n"
	    		  		+ "					COALESCE(S_Bank_amt,0) S_Bank_amt,\r\n"
	    		  		+ "					row_num1,voucher_no1,lf_no1,transaction_date1, account_main_head1, account_sub_head1,\r\n"
	    		  		+ "	                transaction_id1,cash_bank1, description1,\r\n"
	    		  		+ "					COALESCE(Cash_amt1,0) Cash_amt1,\r\n"
	    		  		+ "					COALESCE(Bank_amt1,0) Bank_amt1,\r\n"
	    		  		+ "					COALESCE(S_Bank_amt1,0) S_Bank_amt1 FROM ( " +
	    		          "SELECT ROW_NUMBER() OVER (ORDER BY 0) AS row_num, a.voucher_no, a.lf_no, a.transaction_date, c.account_main_head, b.account_sub_head, " +
	    		          "a.transaction_id, a.cash_bank, a.description, " +
	    		          "CASE WHEN a.cash_bank = 'Cash' THEN a.amount ELSE 0 END AS Cash_amt, " +
	    		          "CASE WHEN a.cash_bank = 'Bank' THEN a.amount ELSE 0 END AS Bank_amt, " +
	    		          "CASE WHEN a.cash_bank = 'Saving Bank' THEN a.amount ELSE 0 END AS S_Bank_amt " +
	    		          "FROM public.school_transaction a " +
	    		          "LEFT JOIN public.account_sub_head b ON a.account_type_id = b.account_type_id " +
	    		          "AND a.account_main_head = b.account_main_head_id " +
	    		          "AND a.account_sub_head_id = b.account_sub_head_id " +
	    		          "LEFT JOIN public.account_main_head c ON a.account_type_id = c.account_type_id " +
	    		          "AND a.account_main_head = c.account_main_head_id " +
	    		          "WHERE a.account_type_id = :account_type_id AND a.transaction_date BETWEEN :fromDate AND :toDate " +
	    		          "ORDER BY a.transaction_id " +
	    		          ") A " +
	    		          "FULL JOIN ( " +
	    		          "SELECT ROW_NUMBER() OVER (ORDER BY 0) AS row_num1, a.voucher_no AS voucher_no1, a.lf_no AS lf_no1, a.transaction_date AS transaction_date1, " +
	    		          "c.account_main_head AS account_main_head1, b.account_sub_head AS account_sub_head1, a.transaction_id AS transaction_id1, " +
	    		          "a.cash_bank AS cash_bank1, a.description AS description1, " +
	    		          "CASE WHEN a.cash_bank = 'Cash' THEN a.amount ELSE 0 END AS Cash_amt1, " +
	    		          "CASE WHEN a.cash_bank = 'Bank' THEN a.amount ELSE 0 END AS Bank_amt1, " +
	    		          "CASE WHEN a.cash_bank = 'Saving Bank' THEN a.amount ELSE 0 END AS S_Bank_amt1 " +
	    		          "FROM public.school_transaction a " +
	    		          "LEFT JOIN public.account_sub_head b ON a.account_type_id = b.account_type_id " +
	    		          "AND a.account_main_head = b.account_main_head_id " +
	    		          "AND a.account_sub_head_id = b.account_sub_head_id " +
	    		          "LEFT JOIN public.account_main_head c ON a.account_type_id = c.account_type_id " +
	    		          "AND a.account_main_head = c.account_main_head_id " +
	    		          "WHERE a.account_type_id = :account_type_id1 AND a.transaction_date BETWEEN :fromDate AND :toDate " +
	    		          "ORDER BY a.transaction_id " +
	    		          ") B ON A.row_num = B.row_num1",
	    		  nativeQuery = true
	    		)
	    		List<Object[]> getCombinedTransactions(
	    		    @Param("fromDate") LocalDate fromDate,//Integer accountTypeId,
	    		    @Param("toDate")LocalDate toDate,// Integer accountTypeId1
	    		    @Param("account_type_id") Integer accountTypeId,
	    		    @Param("account_type_id1")Integer accountTypeId1
	    				);

	
	
}
