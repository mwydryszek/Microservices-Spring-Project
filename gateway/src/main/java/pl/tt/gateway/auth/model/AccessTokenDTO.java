package pl.tt.gateway.auth.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccessTokenDTO {

    private String accessToken;
}
