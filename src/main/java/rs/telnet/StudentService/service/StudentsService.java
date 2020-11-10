package rs.telnet.StudentService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.telnet.StudentService.model.Exam;
import rs.telnet.StudentService.model.Students;
import rs.telnet.StudentService.repository.StudentsRepository;

import java.util.List;

@Service
public  class StudentsService {

    @Autowired
    StudentsRepository studentsRepository;

  /*  public  List<Exam> getExamsByIndex(String index){
        return studentsRepository.findAllById();
    }*/

    public List<Students> getByName(String name) {
        return studentsRepository.queryBy(name);
    }
}
