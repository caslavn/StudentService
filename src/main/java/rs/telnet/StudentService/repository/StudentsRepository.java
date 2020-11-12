package rs.telnet.StudentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.telnet.StudentService.model.Students;

import java.util.List;


@Repository
public interface StudentsRepository extends JpaRepository<Students, String> {

    @Query("select st.name from Students st")
    public List<Students> getNames();

    //public List<Map<Students, String>> getNames();
}


