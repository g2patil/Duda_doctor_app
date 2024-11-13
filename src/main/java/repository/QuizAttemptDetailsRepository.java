package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.QuizAttemptDetails;

public interface QuizAttemptDetailsRepository extends JpaRepository<QuizAttemptDetails, Long> {
}
