package service;

import java.util.List;

import model.QuizAttemptDetails;

public class QuizAttemptRequest {
    private Long userId;
    private Long topicId;
    private String topicName;
    private int score;
    private int totalQuestions;
    private List<QuizAttemptDetails> details;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public List<QuizAttemptDetails> getDetails() {
        return details;
    }

    public void setDetails(List<QuizAttemptDetails> details) {
        this.details = details;
    }
}

