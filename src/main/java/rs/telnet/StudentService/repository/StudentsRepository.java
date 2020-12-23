package rs.telnet.StudentService.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.telnet.StudentService.model.Students;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Students, String> {

    @Query("Select st from Students st where name like %?1%")
    Page<Students> findByName(String name, Pageable pageable);

}