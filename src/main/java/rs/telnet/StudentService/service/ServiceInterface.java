package rs.telnet.StudentService.service;

import rs.telnet.StudentService.model.Students;

import java.util.List;

public interface ServiceInterface {
    public List<Students> findAllStudents();

    public Students findStudentsByIndex(String theIndex);

    public Students saveStudents(Students theStudents);

    public String deleteStudentsByIndex(String theIndex);
}
