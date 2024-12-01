package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.club_m_activity;

@Repository
public interface club_m_activityRepository extends JpaRepository<club_m_activity, Long> {

}
