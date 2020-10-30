package rs.telnet.StudentService.Dao;

import rs.telnet.StudentService.Model.Students;
import java.util.List;

public interface StudentServiceDAO {

     List<Students> getAllStudents();

     Students findAllStudentsByIndex(String theIndex);

     Students saveStudents(Students theStudents);

     void deleteStudentsByIndex(String theIndex);
}
