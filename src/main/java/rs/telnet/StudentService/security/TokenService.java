package rs.telnet.StudentService.security;

import static java.util.stream.Collectors.toList;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import rs.telnet.StudentService.model.Role;
import rs.telnet.StudentService.model.User;
import rs.telnet.StudentService.security.exception.AuthenticationException;

@Service
public class TokenService {
  private String accessTokenSecretKey;

  private String refreshTokenSecretKey;

  private long accessTokenValidityInMilliseconds;

  private long refreshTokenValidityInMilliseconds;

  private UserDetailsService userDetailsService;

  private AuthenticationTokenService authenticationTokenService;

  @Autowired
  public TokenService(UserDetailsService userDetailsService,
                      AuthenticationTokenService authenticationTokenService,
                      Properties properties) {
    this.userDetailsService = userDetailsService;
    this.authenticationTokenService = authenticationTokenService;
    accessTokenSecretKey = properties.getAccessTokenSecretKey();
    accessTokenValidityInMilliseconds = properties.getAccessTokenValidityInMilliseconds();
    refreshTokenSecretKey = properties.getRefreshTokenSecretKey();
    refreshTokenValidityInMilliseconds = properties.getRefreshTokenValidityInMilliseconds();
  }

  public Tokens createToken(User user) {
    Tokens token = new Tokens();
    long expiresIn = expiration(accessTokenValidityInMilliseconds);

    token.setAccessToken(createAccessToken(user));
    token.setRefreshToken(createRefreshToken(user));
    token.setExpiresIn(expiresIn);

    return token;
  }

  Authentication getAuthentication(AuthenticationToken token) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(token.getUsername());
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  String getUsernameFromRefreshToken(String token) throws JwtException {
    return Jwts.parser().setSigningKey(refreshTokenSecretKey).parseClaimsJws(token).getBody().getSubject();
  }

  AuthenticationToken resolveToken(HttpServletRequest req) throws AuthenticationException {
    String bearer = "Bearer ";
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken == null) {
      throw new AuthenticationException("Authorization header should be present");
    }
    if (!bearerToken.startsWith(bearer)) {
      throw new AuthenticationException("Authorization header should begin with Bearer");
    }

    return authenticationTokenService.createToken(bearerToken.substring(bearer.length()));
  }

  private String createAccessToken(User user) {
    long expiresIn = expiration(accessTokenValidityInMilliseconds);

    return createToken(user, expiresIn, accessTokenSecretKey);
  }

  private String createRefreshToken(User user) {
    long expiresIn = expiration(refreshTokenValidityInMilliseconds);

    return createToken(user, expiresIn, refreshTokenSecretKey);
  }

  private List<String> getRoleNames(Set<Role> roles) {
    return roles.stream().map(Role::getName).collect(toList());
  }

  private String createToken(User user, long expiresIn, String key) {
    Claims claims = Jwts.claims();

    claims.setSubject(user.getUsername());
    claims.put("username", String.join(user.getUsername()));
    claims.put("role", getRoleNames(user.getRoles()));
    Date now = new Date();
    Date expirationDate = new Date(expiresIn);

    return Jwts.builder()
      .setClaims(claims)
      .setIssuedAt(now)
      .setExpiration(expirationDate)
      .signWith(SignatureAlgorithm.HS256, key)
      .compact();
  }

  private long expiration(long validity) {
    Date now = new Date();
    return now.getTime() + validity;
  }

  public String getAccessTokenSecretKey() {
    return accessTokenSecretKey;
  }
}
