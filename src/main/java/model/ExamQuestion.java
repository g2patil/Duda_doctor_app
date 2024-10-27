package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_question"
,uniqueConstraints = {@UniqueConstraint(columnNames = {"question_id", "s_topic_id"})}
)
public class ExamQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_topic_id")
   // @Column(name = "m_topic_id")
    @JsonBackReference
    private ExamMTopic mTopic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s_topic_id")
    @JsonBackReference
    private ExamSTopic sTopic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diff_level_id")
    @JsonBackReference
    private ExamDiffLevel diffLevel;
    
    @Column(unique = true, nullable = false) 
    private String que;
    private String ansA;
    private String ansB;
    private String ansC;
    private String ansD;
    private String correctAnswer;
    private String expln;
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public ExamMTopic getmTopic() {
		return mTopic;
	}
	public void setmTopic(ExamMTopic mTopic) {
		this.mTopic = mTopic;
	}
	public ExamSTopic getsTopic() {
		return sTopic;
	}
	public void setsTopic(ExamSTopic sTopic) {
		this.sTopic = sTopic;
	}
	public ExamDiffLevel getDiffLevel() {
		return diffLevel;
	}
	public void setDiffLevel(ExamDiffLevel diffLevel) {
		this.diffLevel = diffLevel;
	}
	public String getQue() {
		return que;
	}
	public void setQue(String que) {
		this.que = que;
	}
	public String getAnsA() {
		return ansA;
	}
	public void setAnsA(String ansA) {
		this.ansA = ansA;
	}
	public String getAnsB() {
		return ansB;
	}
	public void setAnsB(String ansB) {
		this.ansB = ansB;
	}
	public String getAnsC() {
		return ansC;
	}
	public void setAnsC(String ansC) {
		this.ansC = ansC;
	}
	public String getAnsD() {
		return ansD;
	}
	public void setAnsD(String ansD) {
		this.ansD = ansD;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String getExpln() {
		return expln;
	}
	public void setExpln(String expln) {
		this.expln = expln;
	}

    // Getters and Setters
    
}

