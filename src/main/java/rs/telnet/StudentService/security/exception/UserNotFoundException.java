package rs.telnet.StudentService.security.exception;

import rs.telnet.StudentService.exception.ApiException;

public class UserNotFoundException extends ApiException {

  private static final long serialVersionUID = -5258959358527382145L;
  public UserNotFoundException(String message) {
    super(message);
  }
}
