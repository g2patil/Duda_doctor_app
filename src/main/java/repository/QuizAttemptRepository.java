package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.QuizAttempt;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
}
