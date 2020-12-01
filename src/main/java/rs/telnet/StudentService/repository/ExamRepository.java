package rs.telnet.StudentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.telnet.StudentService.dto.ExamResponse;
import rs.telnet.StudentService.dto.GradeResponse;
import rs.telnet.StudentService.model.Exam;
import rs.telnet.StudentService.model.Students;
import rs.telnet.StudentService.model.Subject;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Map;

@Repository
public interface ExamRepository extends JpaRepository<Exam, String> {

    @Query("select new rs.telnet.StudentService.dto.ExamResponse (st.name, st.surname, ex.grade, su.subject_name) " +
            "from Students st " +
            "join Exam ex " +
            "on st.index = ex.students " +
            "join Subject su " +
            "on su.id = ex.subject " +
            "order by su.subject_name")
    public List<ExamResponse> findAllExams();

    @Query("select new rs.telnet.StudentService.dto.GradeResponse (st.name, st.surname, su.subject_name) " +
            "from Students st " +
            "join Exam ex " +
            "on st.index = ex.students " +
            "join Subject su " +
            "on su.id = ex.subject " +
            "where ex.grade = 5 ")
    public List<GradeResponse> findStudentsWhoFailed();

}
