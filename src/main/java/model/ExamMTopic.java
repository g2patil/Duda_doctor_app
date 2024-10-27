package model;

import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "exam_m_topic")
public class ExamMTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_topic_id")
    private Long mTopicId;

    @Column(name = "m_topic_name", nullable = false, unique = true)
    private String mTopicName;

    @OneToMany(mappedBy = "mTopic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<ExamSTopic> subTopics;

    // No-argument constructor
    public ExamMTopic() {
    }

    // Parameterized constructor (if needed)
    public ExamMTopic(String mTopicName) {
        this.mTopicName = mTopicName;
    }

    // Getters and Setters
   /* public Long getMTopicId() {
        return mTopicId;
    }*/

    public void setMTopicId(Long mTopicId) {
        this.mTopicId = mTopicId;
    }

   /* public String getMTopicName() {
        return mTopicName;
    }*/

    public Long getmTopicId() {
		return mTopicId;
	}

	public void setmTopicId(Long mTopicId) {
		this.mTopicId = mTopicId;
	}

	public String getmTopicName() {
		return mTopicName;
	}

	public void setmTopicName(String mTopicName) {
		this.mTopicName = mTopicName;
	}

	public void setMTopicName(String mTopicName) {
        this.mTopicName = mTopicName;
    }

    public Set<ExamSTopic> getSubTopics() {
        return subTopics;
    }

    public void setSubTopics(Set<ExamSTopic> subTopics) {
        this.subTopics = subTopics;
    }
}
