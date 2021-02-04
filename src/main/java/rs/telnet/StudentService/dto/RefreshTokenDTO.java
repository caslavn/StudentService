package rs.telnet.StudentService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import rs.telnet.StudentService.security.Tokens;

public class RefreshTokenDTO {

  @NotNull
  @JsonProperty("token")
  private Tokens tokens;

  public RefreshTokenDTO() {
  }

  public RefreshTokenDTO(@NotNull Tokens tokens) {
    this.tokens = tokens;
  }

  public Tokens getTokens() {
    return tokens;
  }

  public void setTokens(Tokens tokens)     {
    this.tokens = tokens;
  }
}
