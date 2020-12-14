package rs.telnet.StudentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.telnet.StudentService.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}