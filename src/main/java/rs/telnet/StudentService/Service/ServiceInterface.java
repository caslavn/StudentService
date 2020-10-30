package rs.telnet.StudentService.Service;

import rs.telnet.StudentService.Model.Students;

import java.util.List;

public interface ServiceInterface {
    public List<Students> findAllStudents();

    public Students findStudentsByIndex(String theIndex);

    public Students saveStudents(Students theStudents);

    public String deleteStudentsByIndex(String theIndex);
}
