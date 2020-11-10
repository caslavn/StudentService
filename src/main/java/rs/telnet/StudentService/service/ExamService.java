package rs.telnet.StudentService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.telnet.StudentService.model.Exam;
import rs.telnet.StudentService.repository.ExamRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public abstract class ExamService implements MainService<Exam>{

    /*@Autowired
    ExamRepository examRepository;

    public List<Exam> getAllBy(String indexNumber, int id) {
        return examRepository.queryBy(indexNumber, id);
    }

    public void saveExam(Exam exam) {
        examRepository.save(exam);
    }

    @Autowired
    private ExamRepository examRepository;

    public List<Exam> getAllExams(String index, int id) {
        return examRepository.queryBy(index, id);
    }*/




}
