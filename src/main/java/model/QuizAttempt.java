package model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "quiz_attempt")
public class QuizAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attemptId;

    private Long userId;
    private Long topicId;
    private String topicName;
    private Integer score;
    private Integer totalQuestions;
    private Double percentage;
    private LocalDateTime attemptDate = LocalDateTime.now();
	public Long getAttemptId() {
		return attemptId;
	}
	public void setAttemptId(Long attemptId) {
		this.attemptId = attemptId;
	}
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
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getTotalQuestions() {
		return totalQuestions;
	}
	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public LocalDateTime getAttemptDate() {
		return attemptDate;
	}
	public void setAttemptDate(LocalDateTime attemptDate) {
		this.attemptDate = attemptDate;
	}
    
    
}

