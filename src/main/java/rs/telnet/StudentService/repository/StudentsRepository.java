package rs.telnet.StudentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.telnet.StudentService.dto.StudentsResponse;
import rs.telnet.StudentService.model.Students;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Students, String> {

    //@Query("select * from students st where st.name")
    //List<Students> findStudentsByNameUsingQuery(String name);

    @Query(value = "SELECT * FROM students s WHERE s.name = ?1", nativeQuery = true)
    public List<Students> queryBy(@Param("name") String name);


}


