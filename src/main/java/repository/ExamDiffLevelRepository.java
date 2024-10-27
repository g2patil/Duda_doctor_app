package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.ExamDiffLevel;
import model.ExamMTopic;

@Repository
public interface ExamDiffLevelRepository extends JpaRepository<ExamDiffLevel, Long> {

	
	
}
