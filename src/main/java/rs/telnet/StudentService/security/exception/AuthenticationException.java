package rs.telnet.StudentService.security.exception;

public class AuthenticationException extends Exception {

  public AuthenticationException(String message) {
    super(message);
  }

  public AuthenticationException() {
  }
}
