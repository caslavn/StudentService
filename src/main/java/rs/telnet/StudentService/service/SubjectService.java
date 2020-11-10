package rs.telnet.StudentService.service;

import org.springframework.stereotype.Service;
import rs.telnet.StudentService.model.Exam;
import rs.telnet.StudentService.model.Subject;

import java.util.List;

@Service
public abstract class SubjectService implements MainService<Subject>{

    public abstract List<Exam> getExamsByIndex(String index_number);
}
