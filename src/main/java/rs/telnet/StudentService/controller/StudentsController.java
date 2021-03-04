package rs.telnet.StudentService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.telnet.StudentService.model.Students;
import rs.telnet.StudentService.repository.StudentsRepository;

import java.util.*;

@RestController
@RequestMapping("/api/student")
public class StudentsController extends Students {

    @Autowired
    StudentsRepository studentsRepository;

    @GetMapping
    public List<Students> getAllStudents() {
        List<Students> allStudentslist = studentsRepository.findAll();
        return allStudentslist;
    }

    @GetMapping("/filter")
    public ResponseEntity<Map<String, Object>> getAllTutorials(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sort){

        try {
        List<Students> students;
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));

        Page<Students> pageTuts;
        if (name == null)
            pageTuts = studentsRepository.findAll(paging);
        else
            pageTuts = studentsRepository.findByName(name, paging);

        students = pageTuts.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("students", students);
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalItems", pageTuts.getTotalElements());
        response.put("totalPages", pageTuts.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin")
    public Students createStudents(@RequestBody Students students) {
        Students saveStudents = studentsRepository.save(students);
        return saveStudents;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/admin")
    public Map<String, Boolean> deleteStudents(@RequestParam(value = "index") String studentsId) {
        Students students = studentsRepository.findById(studentsId).get();

        studentsRepository.delete(students);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }
}
