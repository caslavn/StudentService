package rs.telnet.StudentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.telnet.StudentService.dto.ExamResponse;
import rs.telnet.StudentService.model.Exam;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, String> {

    /*@Query( value = "select st.name , st.surname, ex.grade, su.name" +
            "from students s " +
            "join exam ex " +
            "   on st.index_number = ex.index_number " +
            "join subject su " +
            "   on su.id = ex.subject_id ",
            nativeQuery = true)
    List<Exam> queryBy(@Param("students_id") String indexNumber,
                       @Param("subject_id") int id);

    @Query(value = "SELECT * FROM exam bs WHERE " +
            "EXISTS (SELECT 1 FROM customer st WHERE ex.students_id = st.id AND st.index = :index) " +
            "AND EXISTS (SELECT 1 FROM subject sb WHERE sb.id = sb.subject_id AND sb.name IN :name)",
            nativeQuery = true)
    List<Exam> queryBy(@Param("students_id") String index,
                       @Param("subject_id") int id);*/



}
