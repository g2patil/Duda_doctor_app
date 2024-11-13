package model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "quiz_attempt_details")
public class QuizAttemptDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;

    @ManyToOne
    @JoinColumn(name = "attempt_id", nullable = false)
    private QuizAttempt quizAttempt;

    private Long questionId;
    private String userAnswer;
    private String correctAnswer;
	public Long getDetailId() {
		return detailId;
	}
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	public QuizAttempt getQuizAttempt() {
		return quizAttempt;
	}
	public void setQuizAttempt(QuizAttempt quizAttempt) {
		this.quizAttempt = quizAttempt;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
   
    
    
}
