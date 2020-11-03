package rs.telnet.StudentService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.telnet.StudentService.model.Students;
import rs.telnet.StudentService.service.ServiceInterface;

import java.util.List;

@RestController
@RequestMapping(path="/demo")
public class StudentServiceController {

    @Autowired
    private ServiceInterface serviceInterface;

    @Autowired
    public StudentServiceController(ServiceInterface theserviceinterface){
        serviceInterface = theserviceinterface;
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<List<Students>> findAll(){
        System.out.println(serviceInterface.findAllStudents().size());
        return new ResponseEntity<List<Students>>(serviceInterface.findAllStudents(), HttpStatus.OK);
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public Students addStudents(@RequestBody Students theStudents){
        return (serviceInterface.saveStudents(theStudents));
    }

    @RequestMapping(value = "/students", method = RequestMethod.PUT)
    public Students updateStudents(@RequestBody Students theStudents){
        Students students = serviceInterface.findStudentsByIndex(theStudents.getIndexNumber());
        if (students == null){
            throw new RuntimeException("Student to update doesn't exist.");
        }
        return (serviceInterface.saveStudents(theStudents));
    }

    @RequestMapping(value = "/students", method = RequestMethod.DELETE)
    public String deleteStudents(@RequestParam String studentsId){
        Students students  = serviceInterface.findStudentsByIndex(studentsId);
        if (students == null) {
            throw new RuntimeException("Student's index not found.");
        }
        serviceInterface.deleteStudentsByIndex(studentsId );
        return "deleted student indexNumber" + studentsId;
    }


}
