package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.ExamMTopic;
import model.ExamQuestion;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Long> {

	 List<ExamQuestion> findByMTopic_mTopicIdAndSTopic_sTopicId(Long mTopicId,Long sTopicId);

	  List<ExamQuestion> findByMTopic(ExamMTopic mTopic);

}
