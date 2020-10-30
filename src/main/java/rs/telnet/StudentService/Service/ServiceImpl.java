package rs.telnet.StudentService.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.telnet.StudentService.Dao.StudentServiceDAO;
import rs.telnet.StudentService.Model.Students;

import java.util.List;

@Service
public class ServiceImpl implements ServiceInterface {
    StudentServiceDAO studentServiceDAO;

        @Autowired
        public ServiceImpl(@Qualifier("StudentServiceDAOJpaImpl") StudentServiceDAO theStudentServiceDao){
            studentServiceDAO = theStudentServiceDao;
        }

        @Override
        @Transactional
        public List<Students> findAllStudents() {
            return studentServiceDAO.getAllStudents();
        }


        @Override
        @Transactional
        public Students findStudentsByIndex(String theIndex) {
            return studentServiceDAO.findAllStudentsByIndex(theIndex);
        }

        @Override
        @Transactional
        public Students saveStudents(Students theStudents) {

            return studentServiceDAO.saveStudents(theStudents);
        }

        @Override
        @Transactional
        public String deleteStudentsByIndex(String theIndex) {
            studentServiceDAO.deleteStudentsByIndex(theIndex);
            return theIndex;
        }
}
