package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import model.club_s_activity;

@Repository
public interface club_s_activityRepository extends JpaRepository<club_s_activity, Long> {

	
	//List<club_s_activity> findByClub_m_activity_Id(Long mainActivityId);
	//List<club_s_activity> findByClub_m_activity_Id(Long mainActivityId);
	//List<club_s_activity> findByClubMActivityId(Long mainActivityId);
	//List<club_s_activity> findByClubMActivityId(Long mainActivityId);


}
