package rs.telnet.StudentService.model;

import javax.persistence.*;
@Entity
@Table(name = "exam")
public class Exam {

    @Column(name = "grade")
    private int grade;

    @Id
    @Column(name = "student_index")
    private int  student_index;

    @Id
    @Column(name = "subject_id")
    private int subject_id;

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getStudent_index() {
        return student_index;
    }

    public void setStudent_index(int student_index) {
        this.student_index = student_index;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    @ManyToOne
    @MapsId("studentsIndexNumber")
    @JoinColumn(name = "index_number")
    Students student;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "id")
    Subject subject;
}
