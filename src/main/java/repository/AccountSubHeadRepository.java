package repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.AccountSubHead;

public interface AccountSubHeadRepository extends JpaRepository<AccountSubHead, Long> {
	
	  @Query(value = """
	  		SELECT sh.account_Sub_Head_Id, mh.account_Main_Head_Id, at.account_Type_Id , 
	mh.account_Main_Head 	, at.account_Type   FROM Account_Sub_Head sh left join
	account_Main_Head mh  on sh.account_Main_Head_Id=mh.account_Main_Head_Id
	left join account_Type at on mh.account_Type_Id=at.account_Type_Id
   WHERE sh.account_Sub_Head_Id =:id 
	    	    """, nativeQuery = true)
	    	List<Map<String, Object>> getAccountSubids(@Param("id") Long id);

}
