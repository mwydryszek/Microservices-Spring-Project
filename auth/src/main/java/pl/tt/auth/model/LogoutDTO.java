package pl.tt.auth.model;

import lombok.Value;

@Value
public class LogoutDTO {

    private String accessToken;
    private String refreshToken;

}
