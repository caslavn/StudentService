package rs.telnet.StudentService.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.telnet.StudentService.dto.ExamResponse;
import rs.telnet.StudentService.model.Exam;
import rs.telnet.StudentService.model.Students;
import rs.telnet.StudentService.repository.ExamRepository;
import rs.telnet.StudentService.service.ExamService;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
public class ExamController {

    @Autowired
    ExamRepository examRepository;


    @GetMapping("/get-all")
    public List<Exam> getAllExam(){
        List<Exam> examList = examRepository.findAll();
        return examList;
    }


}
