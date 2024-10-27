package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.ExamMTopic;
import model.ExamSTopic;

@Repository
public interface ExamSTopicRepository extends JpaRepository<ExamSTopic, Long> {

	List<ExamSTopic> findBymTopic_mTopicId(Long mTopicId);



	
}
