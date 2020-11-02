package rs.telnet.StudentService.dao;

import rs.telnet.StudentService.model.Students;
import java.util.List;

public interface StudentServiceDAO {

     List<Students> getAllStudents();

     Students findAllStudentsByIndex(String theIndex);

     Students  saveStudents(Students theStudents);

     void deleteStudentsByIndex(String theIndex);
}
