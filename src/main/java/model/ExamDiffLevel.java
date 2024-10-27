package model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "exam_diff_level")
public class ExamDiffLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diff_level_id")
    private Long diffLevelId;

    @Column(name = "level_name", nullable = false, unique = true)
    private String levelName;

    @OneToMany(mappedBy = "diffLevel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ExamQuestion> questions;

    // Getters and Setters
    public Long getDiffLevelId() {
        return diffLevelId;
    }

    public void setDiffLevelId(Long diffLevelId) {
        this.diffLevelId = diffLevelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Set<ExamQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<ExamQuestion> questions) {
        this.questions = questions;
    }
    
}
