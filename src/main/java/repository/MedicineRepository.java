package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import model.Medicine;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    
    // Custom query to find medicines by name (case insensitive)
	 @Query("SELECT m.name FROM Medicine m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	    List<String> findMedicineNamesByNameContainingIgnoreCase(String name);

	
	
   // List<Medicine> findByNameContainingIgnoreCase(String name);
}
