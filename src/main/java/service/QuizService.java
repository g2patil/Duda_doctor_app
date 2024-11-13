package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.QuizAttempt;
import model.QuizAttemptDetails;
import repository.QuizAttemptDetailsRepository;
import repository.QuizAttemptRepository;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizAttemptRepository quizAttemptRepository;

    @Autowired
    private QuizAttemptDetailsRepository quizAttemptDetailsRepository;

    @Transactional
    public QuizAttempt saveQuizAttempt(Long userId, Long topicId, String topicName, Integer score, Integer totalQuestions, List<QuizAttemptDetails> details) {
        QuizAttempt attempt = new QuizAttempt();
        attempt.setUserId(userId);
        attempt.setTopicId(topicId);
        attempt.setTopicName(topicName);
        attempt.setScore(score);
        attempt.setTotalQuestions(totalQuestions);
        attempt.setPercentage((score.doubleValue() / totalQuestions) * 100);

        QuizAttempt savedAttempt = quizAttemptRepository.save(attempt);

        for (QuizAttemptDetails detail : details) {
            detail.setQuizAttempt(savedAttempt);
            quizAttemptDetailsRepository.save(detail);
        }

        return savedAttempt;
    }
}

