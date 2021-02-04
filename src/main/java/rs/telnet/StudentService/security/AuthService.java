package rs.telnet.StudentService.security;

import rs.telnet.StudentService.dto.LoginDTO;
import rs.telnet.StudentService.dto.RefreshTokenDTO;
import rs.telnet.StudentService.security.exception.InvalidTokenHttpException;
import rs.telnet.StudentService.security.exception.UserNotFoundHttpException;
import rs.telnet.StudentService.model.User;
import rs.telnet.StudentService.service.UserService;
import rs.telnet.StudentService.security.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private UserService userService;
  private AuthenticationManager authenticationManager;
  private TokenService tokenService;

  @Autowired
  public AuthService(UserService userService,
                     AuthenticationManager authenticationManager,
                     TokenService tokenService) {
    this.userService = userService;
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  Tokens login(LoginDTO loginDTO) throws UserNotFoundHttpException {
    try {
      Authentication authentication = createAuthentication(loginDTO);
      MyUserDetailsService.MyUserDetails userDetails =
        (MyUserDetailsService.MyUserDetails) authenticationManager
          .authenticate(authentication).getPrincipal();
      User user = userDetails.getUser();
      return createToken(user);
    } catch (AuthenticationException exception) {
      throw new UserNotFoundHttpException("Incorrect email or password", HttpStatus.FORBIDDEN);
    }
  }

  Tokens refreshToken(RefreshTokenDTO refreshTokenDTO) throws InvalidTokenHttpException {
    try {
      String username = tokenService.getUsernameFromRefreshToken(refreshTokenDTO.getTokens().getRefreshToken());
      User user = userService.findByUsername(username);
      return createToken(user);
    } catch (JwtException | UserNotFoundException e) {
      throw new InvalidTokenHttpException();
    }
  }


  private Authentication createAuthentication(LoginDTO loginDTO) {
    return new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
  }

  private Tokens createToken(User user) {
    return tokenService.createToken(user);
  }

}
