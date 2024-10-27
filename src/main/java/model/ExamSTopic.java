package model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_s_topic",uniqueConstraints = {@UniqueConstraint(columnNames = {"m_topic_id", "s_topic_id"})})
public class ExamSTopic {
    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_topic_id", nullable = false)
    private Long sTopicId;

    @Column(name = "s_topic_name", nullable = false)
    private String sTopicName;

    @ManyToOne
    @JoinColumn(name = "m_topic_id", nullable = false)
    @JsonManagedReference
    private ExamMTopic mTopic;

    // Getters and Setters
  /*  public Long getSTopicId() {
        return sTopicId;
    }*/

    public void setSTopicId(Long sTopicId) {
        this.sTopicId = sTopicId;
    }

    public String getSTopicName() {
        return sTopicName;
    }

    public void setSTopicName(String sTopicName) {
        this.sTopicName = sTopicName;
    }

  /*  public ExamMTopic getMTopic() {
        return mTopic;
    }

    public void setMTopic(ExamMTopic mTopic) {
        this.mTopic = mTopic;
    }
*/
	public Long getsTopicId() {
		// TODO Auto-generated method stub
		return this.sTopicId;
	}

	public void setSubTopics(List<ExamSTopic> collect) {
		// TODO Auto-generated method stub
		
	}
}
