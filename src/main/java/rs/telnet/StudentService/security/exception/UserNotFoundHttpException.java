package rs.telnet.StudentService.security.exception;

import rs.telnet.StudentService.exception.HttpException;
import org.springframework.http.HttpStatus;

public class UserNotFoundHttpException extends HttpException {
    private static final long serialVersionUID = 4770986620665158856L;

    public UserNotFoundHttpException(String message, HttpStatus status) {
        super(message, status);
    }
}