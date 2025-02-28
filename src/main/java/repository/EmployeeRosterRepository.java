package repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.EntityManager;
import model.EmployeeRoster;

public interface EmployeeRosterRepository extends JpaRepository<EmployeeRoster, Long> {

	
	
	
	
	@Query(value = """
	        SELECT 
	            A.bindu_id,
	            A.bindu_name,
	            A.employee_name,
	            A.reservation_category,
	            B.bindu_name_mar,
	            A.date_of_promotion,
	            A.date_of_appointment,
	            A.date_of_birth, 
	            A.date_of_retirement,
	            A.caste_certificate_number,
	            A.caste_certificate_date,
	            A.caste_certificate_issuing_authority,
	            A.caste_validity_certificate_number,
	            A.caste_validity_certificate_date,
	            A.comments
	        FROM public.employee_roster A,
	             public.maharashtra_reservation B
	        WHERE TO_NUMBER(A.bindu_code, '99999') = B.resv_cat_id
	        AND A.institute_id =1
	        ORDER BY A.bindu_id
	        """, nativeQuery = true)
	    List<Object[]> getAllEmployeeRoster();
	    
	   @Query(value = """
		        SELECT 
		            A.bindu_id,
		            A.bindu_name,
		            A.employee_name,
		            A.reservation_category,
		            B.bindu_name_mar,
		            A.date_of_promotion,
		            A.date_of_appointment,
		            A.date_of_birth, 
		            A.date_of_retirement,
		            A.caste_certificate_number,
		            A.caste_certificate_date,
		            A.caste_certificate_issuing_authority,
		            A.caste_validity_certificate_number,
		            A.caste_validity_certificate_date,
		            A.comments
		        FROM public.employee_roster A,
		             public.maharashtra_reservation B
		        WHERE TO_NUMBER(A.bindu_code, '99999') = B.resv_cat_id
		        AND A.institute_id = :instituteId
		        ORDER BY A.bindu_id
		        """, nativeQuery = true)
	    List<Object[]> findAllEmployeeRoster(@Param("instituteId") Long instituteId);
		
	    
	    @Query(value = """
	               SELECT 
	                 COUNT(CASE WHEN reservation_category = '1' THEN 1 END)   AS "1",
	                 COUNT(CASE WHEN reservation_category ='2' THEN 1 END)    AS "2",
	                 COUNT(CASE WHEN reservation_category ='3' THEN 1 END)    AS "3",
	                 COUNT(CASE WHEN reservation_category ='4' THEN 1 END)    AS "4",
	                 COUNT(CASE WHEN reservation_category = '5' THEN 1 END)    AS "5",
	                 COUNT(CASE WHEN reservation_category ='6' THEN 1 END)    AS "6",
	                 COUNT(CASE WHEN reservation_category ='7' THEN 1 END)    AS "7",
	                 COUNT(CASE WHEN reservation_category ='8' THEN 1 END)    AS "8",
	                 COUNT(CASE WHEN reservation_category = '9' THEN 1 END)    AS "9",
	                 COUNT(CASE WHEN reservation_category ='10' THEN 1 END)    AS "10",
	                 COUNT(CASE WHEN reservation_category ='11' THEN 1 END)    AS "11",
	                 COUNT(CASE WHEN reservation_category ='12' THEN 1 END)    AS "12"
	            FROM public.employee_roster A  WHERE  A.institute_id = :instituteId
	            """, nativeQuery = true)
	       // List<Map<String, Object>> getReservationSummary(); 
	    List<Map<String, Object>> getReservationSummary(@Param("instituteId") Long instituteId);
		

}
