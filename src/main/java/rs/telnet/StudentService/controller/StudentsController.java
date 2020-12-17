package rs.telnet.StudentService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.telnet.StudentService.model.Students;
import rs.telnet.StudentService.repository.StudentsRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentsController extends Students {

    @Autowired
    StudentsRepository studentsRepository;

    @GetMapping
    public List<Students> getAllStudents(){
        List<Students> allStudentslist = studentsRepository.findAll();
        return allStudentslist;
    }

    @PostMapping("/admin")
    public Students createStudents(@RequestBody Students students) {
        Students saveStudents = studentsRepository.save(students);
        return saveStudents;
    }

    @DeleteMapping("/admin ")
    public Map<String, Boolean> deleteStudents(@RequestParam(value = "index") String studentsId)
    {
        Students students = studentsRepository.findById(studentsId).get();

        studentsRepository.delete(students);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}