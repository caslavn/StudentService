package rs.telnet.StudentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.telnet.StudentService.model.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, String> {

}


