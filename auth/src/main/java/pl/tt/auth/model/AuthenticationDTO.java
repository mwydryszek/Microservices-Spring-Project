package pl.tt.auth.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationDTO {

    private String accessToken;
    private Long accessTokenValidityTime;
    private String refreshToken;
    private Long refreshTokenValidityTime;
}
