package repository;

import java.time.LocalDate;
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
	    	    SELECT resv_cat, MIN(reservation_date) AS min_reservation_date
	    	    FROM public.reservation_dates A 
	    	    WHERE A.reservation_date > CAST(:startDate AS DATE) 
	    	    GROUP BY resv_cat
	    	    """, nativeQuery = true)
	    	List<Map<String, Object>> getReservationByDate(@Param("startDate") String startDate);

	    @Query(value = """
				SELECT er.reservation_category, mr.bindu_name, mr.bindu_name_mar,mr.percentage,
				string_agg(DISTINCT er.bindu_id::text, ', ') AS bindu_nos,count(*) fill_nos
				FROM public.maharashtra_reservation mr,public.employee_roster er
				where mr.resv_cat_id=to_number(er.bindu_code,'999')
				AND er.institute_id=:instituteId
				group by er.reservation_category, mr.bindu_name_mar, mr.bindu_name,mr.percentage
				order by to_number(er.reservation_category,'99999')
	    	    """, nativeQuery = true)
	    	List<Map<String, Object>> getgoshwaraByCat(@Param("instituteId") Long instituteId);
	    
	    
	    
	    
	    
	    
	    @Query(value = """
	    	   SELECT id, reservation_date, resv_cat,mr.bindu_name_mar,
               case when resv_cat='11' then 100- ( SELECT sum(percentage)
	           FROM public.reservation_dates rd,public.maharashtra_reservation mr
	           where to_number(rd.resv_cat,'9999')=mr.resv_cat_id and 
	           reservation_date<=CAST(:startDate AS DATE) 	and resv_cat<>'11' ) 
	           else percentage end as per FROM public.reservation_dates 
	           rd,public.maharashtra_reservation mr where to_number(rd.resv_cat,'9999')
	           =mr.resv_cat_id 	and reservation_date<=CAST(:startDate AS DATE)
	           order by id  """, nativeQuery = true)
	    	List<Map<String, Object>> getReservationPerByDate(@Param("startDate") String startDate);
	   
	    
	    
	    
	    
	    
	    @Query(value = """
	    	    SELECT 
	    	        COUNT(CASE WHEN A.reservation_category = '1' 
               AND B.reservation_date <= CAST(:startDate AS DATE) THEN 1 END) AS "1",
    COUNT(CASE WHEN A.reservation_category = '2' 
               AND B.reservation_date <= CAST(:startDate AS DATE) THEN 1 END) AS "2",
    COUNT(CASE WHEN A.reservation_category = '3' 
               AND B.reservation_date <= CAST(:startDate AS DATE) THEN 1 END) AS "3",
    COUNT(CASE WHEN A.reservation_category = '4' 
               AND B.reservation_date <= CAST(:startDate AS DATE) THEN 1 END) AS "4",
    COUNT(CASE WHEN A.reservation_category = '5' 
               AND B.reservation_date <= CAST(:startDate AS DATE) THEN 1 END) AS "5",
    COUNT(CASE WHEN A.reservation_category = '6' 
               AND B.reservation_date <= CAST(:startDate AS DATE) THEN 1 END) AS "6",
    COUNT(CASE WHEN A.reservation_category = '7' 
               AND B.reservation_date <= CAST(:startDate AS DATE) THEN 1 END) AS "7",
    COUNT(CASE WHEN A.reservation_category = '8' 
               AND B.reservation_date <= CAST(:startDate AS DATE) THEN 1 END) AS "8",
    COUNT(CASE WHEN A.reservation_category = '9' 
               AND B.reservation_date <= CAST(:startDate AS DATE) THEN 1 END) AS "9",
    COUNT(CASE WHEN A.reservation_category = '10' 
               AND B.reservation_date <= CAST(:startDate AS DATE) THEN 1 END) AS "10",
    COUNT(CASE WHEN A.reservation_category = '11' 
               AND B.reservation_date <= CAST(:startDate AS DATE) THEN 1 END) AS "11",
    COUNT(CASE WHEN A.reservation_category = '12' 
               AND B.reservation_date <= CAST(:startDate AS DATE) THEN 1 END) AS "12"
	    	    FROM public.employee_roster A  
	    	     JOIN (SELECT resv_cat, MAX(reservation_date) AS reservation_date      FROM public.reservation_dates 
    WHERE reservation_date <= CAST(:startDate AS DATE)    GROUP BY resv_cat) B ON A.bindu_code = B.resv_cat
	    	    WHERE A.institute_id = :instituteId	    	""", nativeQuery = true)
	    	List<Map<String, Object>> getReservationSummary(
	    	    @Param("instituteId") Long instituteId, 
	    	    @Param("startDate") String startDate // Format: 'YYYY-MM-DD'
	    	   // @Param("endDate") String endDate       // Format: 'YYYY-MM-DD'
	    	);
		
	 
	    
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
	       // List<Map<String, Object>> getReservationSummary1(); 
	    List<Map<String, Object>> getReservationSummary(@Param("instituteId") Long instituteId);

		boolean existsByEmployeeNameAndDateOfBirthAndReservationCategory(String employeeName, LocalDate dateOfBirth,
				String reservationCategory);
		

}
