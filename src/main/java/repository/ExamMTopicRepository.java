package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.ExamMTopic;
import model.ExamSTopic;

@Repository
public interface ExamMTopicRepository extends JpaRepository<ExamMTopic, Long> {

	
	
}

