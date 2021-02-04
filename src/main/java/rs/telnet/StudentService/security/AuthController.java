package rs.telnet.StudentService.security;

import rs.telnet.StudentService.dto.LoginDTO;
import rs.telnet.StudentService.dto.RefreshTokenDTO;
import rs.telnet.StudentService.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  /**
   * Login user
   * @param loginDTO user credentials
   * @return generated token
   */
  @PostMapping("/login")
  public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
    Tokens tokens = authService.login(loginDTO);
    return toResponse(tokens);
  }

  /**
   * Sign out. Perform any required actions to log out user, like invalidate user session.
   * Implement your required logic
   * @return result message
   */
  @PostMapping("/sign-out")
  public ResponseEntity<ResponseMessage> logout() {
    return ok(new ResponseMessage("Ok"));
  }

  /**
   * Refresh token
   * @param refreshTokenDTO refresh token
   * @return new token
   */
  @PostMapping("/refresh-token")
  public ResponseEntity<RefreshTokenDTO> refreshToken(@RequestBody RefreshTokenDTO refreshTokenDTO) {
    Tokens tokens = authService.refreshToken(refreshTokenDTO);
    return toResponse(tokens);
  }

  private ResponseEntity<RefreshTokenDTO> toResponse(Tokens tokens) {
    return ok(new RefreshTokenDTO(tokens));
  }
}
