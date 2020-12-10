package rs.telnet.StudentService.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.telnet.StudentService.dto.ExamResponse;
import rs.telnet.StudentService.dto.GradeResponse;
import rs.telnet.StudentService.model.Exam;
import rs.telnet.StudentService.model.Students;
import rs.telnet.StudentService.repository.ExamRepository;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
public class ExamController {

    @Autowired
    ExamRepository examRepository;

    @GetMapping
    public List<ExamResponse> getAllExams() {
       return examRepository.findAllExams();
    }

    @GetMapping("/students-who-failed")
    public List<GradeResponse> getStudentsWhoFailed() {
        return examRepository.findStudentsWhoFailed();
    }

}
//gfuodsgfousehfliaohfoa