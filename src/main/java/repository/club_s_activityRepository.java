package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.club_s_activity;

@Repository
public interface club_s_activityRepository extends JpaRepository<club_s_activity, Long> {


}
