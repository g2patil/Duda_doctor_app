package repository;

import model.OPD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface OPDRepository extends JpaRepository<OPD, UUID> {

	List<OPD> findByVisitDateBetween(LocalDate startDate, LocalDate endDate);

	List<OPD> findByPatientId(Integer patientId);

	
	List<OPD> findByPatientIdAndDoctorId(int patientId, int doctorId);

	 List<OPD> findBydoctoridAndVisitDateBetween(Integer doctorid, LocalDate startDate, LocalDate endDate);


	

	
	
}
