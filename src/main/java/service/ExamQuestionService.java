package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import model.ExamMTopic;
import model.ExamQuestion;
import repository.ExamDiffLevelRepository;
import repository.ExamMTopicRepository;
import repository.ExamQuestionRepository;
import repository.ExamSTopicRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ExamQuestionService {

    @Autowired
    private ExamQuestionRepository examQuestionRepository;
    

    @Autowired
    private ExamMTopicRepository examMTopicRepository;

    @Autowired
    private ExamSTopicRepository examSTopicRepository;

    @Autowired
    private ExamDiffLevelRepository examDiffLevelRepository;
    
    public List<ExamQuestion> getRandomQuestions(ExamMTopic mTopic, int questionCount) {
        int maxAllowedQuestions = 50;
        int minAllowedQuestions = 10;

        // Check if requested question count is less than or equal to max allowed
        if (questionCount > maxAllowedQuestions || questionCount < minAllowedQuestions) {
            throw new IllegalArgumentException("Number of questions cannot exceed " + maxAllowedQuestions);
        }

        // Fetch all questions for the main topic
        List<ExamQuestion> questions = examQuestionRepository.findByMTopic(mTopic);

        // Shuffle the list to randomize question selection
        Collections.shuffle(questions);

        // Select the required number of questions
        if (questions.size() < questionCount) {
            System.out.println("Alert: Not enough questions available. Found only " + questions.size());
            return questions; // Return whatever is available
        }

        return questions.subList(0, questionCount);
    }
    
    
    
    
    public List<ExamQuestion> getQuestionsByTopics(Long mTopicId,Long sTopicId) {
        return examQuestionRepository.findByMTopic_mTopicIdAndSTopic_sTopicId(mTopicId,sTopicId);
    }
    
    public List<ExamQuestion> getAllQuestions() {
        return examQuestionRepository.findAll();
    }

    public Optional<ExamQuestion> getQuestionById(Long questionId) {
        return examQuestionRepository.findById(questionId);
    }

    public void addQuestion(ExamQuestion examQuestion) {
        if (examQuestion.getmTopic() == null) {
        	  throw new IllegalArgumentException("MTopic cannot be null");
        } 
        if (examQuestion.getsTopic() == null) {
            throw new IllegalArgumentException("STopic cannot be null");
        }
        if (examQuestion.getDiffLevel() == null) {
            throw new IllegalArgumentException("DiffLevel cannot be null");
        }

        // Proceed with saving the question
        examQuestionRepository.save(examQuestion);
    }

    public ExamQuestion updateQuestion(Long questionId, ExamQuestion examQuestionDetails) {
        Optional<ExamQuestion> existingQuestion = examQuestionRepository.findById(questionId);
        if (existingQuestion.isPresent()) {
            ExamQuestion examQuestion = existingQuestion.get();
            examQuestion.setQue(examQuestionDetails.getQue());
            examQuestion.setAnsA(examQuestionDetails.getAnsA());
            examQuestion.setAnsB(examQuestionDetails.getAnsB());
            examQuestion.setAnsC(examQuestionDetails.getAnsC());
            examQuestion.setAnsD(examQuestionDetails.getAnsD());
            examQuestion.setCorrectAnswer(examQuestionDetails.getCorrectAnswer());
            examQuestion.setExpln(examQuestionDetails.getExpln());
            examQuestion.setmTopic(examQuestionDetails.getmTopic());
            examQuestion.setsTopic(examQuestionDetails.getsTopic());
            examQuestion.setDiffLevel(examQuestionDetails.getDiffLevel());
            return examQuestionRepository.save(examQuestion);
        } else {
            return null;  // Handle question not found scenario
        }
    }

    public void deleteQuestion(Long questionId) {
        examQuestionRepository.deleteById(questionId);
    }
}

