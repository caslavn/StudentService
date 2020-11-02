package rs.telnet.StudentService.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rs.telnet.StudentService.model.Students;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StudentServiceDAOJpaImpl implements StudentServiceDAO{

    @Autowired
    private EntityManager entityManager;

    @Autowired
    public StudentServiceDAOJpaImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Students> getAllStudents() {
        Query theQuery = (Query) entityManager.createQuery("From Students");
        List<Students> students = theQuery.getResultList();
        return students;
    }

    @Override
    public Students findAllStudentsByIndex(String theIndex) {
        Students theStudents = entityManager.find(Students.class, theIndex);
        return theStudents;
    }

    @Override
    public Students saveStudents(Students theStudents) {
        Students studentsServiceDB = entityManager.merge(theStudents);
        theStudents.setIndexNumber(studentsServiceDB.getIndexNumber());
        return theStudents;
    }

    @Override
    public void deleteStudentsByIndex(String theIndex) {
        Query theQuery = (Query) entityManager.createQuery("delete from Students where theIndex=:indexNumber");
        theQuery.setParameter("theIndex", theIndex);
        theQuery.executeUpdate();
    }
}
