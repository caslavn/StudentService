package rs.telnet.StudentService.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(Exam.class)
@Table(name = "exam")
public class Exam implements Serializable {

    @Column(name = "grade")
    private int grade;

    @JsonManagedReference
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "index_number")
    Students students;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    Subject subject;

}