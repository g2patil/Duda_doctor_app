package service;

import model.ExamMTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ExamMTopicRepository;

import java.util.List;

@Service
public class ExamMTopicService {

    @Autowired
    private ExamMTopicRepository examMTopicRepository;

    public List<ExamMTopic> getAllMTopics() {
        return examMTopicRepository.findAll();
    }
}
