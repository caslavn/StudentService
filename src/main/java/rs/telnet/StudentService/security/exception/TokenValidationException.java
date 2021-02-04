package rs.telnet.StudentService.security.exception;

public class TokenValidationException extends AuthenticationException {

  public TokenValidationException(String message) {
    super(message);
  }

  public TokenValidationException() {
  }
}
