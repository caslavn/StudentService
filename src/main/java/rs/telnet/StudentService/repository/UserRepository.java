package rs.telnet.StudentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.telnet.StudentService.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}