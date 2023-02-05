package pl.tt.gateway.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.tt.gateway.auth.feign.AuthFeignClient;
import pl.tt.gateway.auth.model.AccessTokenDTO;
import pl.tt.gateway.auth.model.CheckTokenDTO;

import java.io.IOException;

@Component
@AllArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authentication";
    public static final String BEARER_PREFIX = "Bearer ";

    private final AuthFeignClient authFeignClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader(AUTHORIZATION_HEADER);

        if (token.startsWith(BEARER_PREFIX)) {
            token = token.replace(BEARER_PREFIX, "");
            ResponseEntity<CheckTokenDTO> checkTokenResponseEntity = authFeignClient.checkToken(AccessTokenDTO.builder().accessToken(token).build());

            if (HttpStatus.OK == checkTokenResponseEntity.getStatusCode() && checkTokenResponseEntity.getBody().getIsValid()) {
                filterChain.doFilter(request, response);
            }else {}

        } else {
            // TODO
            //wyrzucic exception albo nadpisac response
        }


    }
}
