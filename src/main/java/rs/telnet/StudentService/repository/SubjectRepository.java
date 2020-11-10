package rs.telnet.StudentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.telnet.StudentService.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
