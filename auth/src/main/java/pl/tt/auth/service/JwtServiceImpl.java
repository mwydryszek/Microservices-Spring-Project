package pl.tt.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.tt.auth.controller.JwtProperties;
import pl.tt.auth.exception.NotAuthorizedException;
import pl.tt.auth.model.*;
import pl.tt.auth.repository.TokenBlackListRepository;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_CLAIMS = "role";

    private final JwtProperties jwtProperties;
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final TokenBlackListRepository tokenBlackListRepository;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public AuthenticationDTO auth(UsernamePasswordDTO usernamePasswordDTO) {
        UserEntity userEntity = userService.findByUsername(usernamePasswordDTO.getUsername());

        if (!passwordEncoder.matches(usernamePasswordDTO.getPassword(), userEntity.getPassword())) {
            throw new NotAuthorizedException("User password does no match");
        }

        return AuthenticationDTO.builder()
                .accessToken(generateAccessToken(userEntity))
                .accessTokenValidityTime(jwtProperties.getAccessTokenValidityTime())
                .refreshToken(generateRefreshToken(userEntity))
                .refreshTokenValidityTime(jwtProperties.getRefreshTokenValidityTime())
                .build();
    }

    @Override
    public void logout(LogoutDTO logoutDTO) {

        blockToken(logoutDTO.getAccessToken(), jwtProperties.getAccessTokenSecret());
        blockToken(logoutDTO.getRefreshToken(), jwtProperties.getRefreshTokenSecret());
    }

    private void blockToken(String token, String secret){

        if (!StringUtils.isEmpty(token)) {

            try {
                Jws<Claims> tokenClaims = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(token);

                TokenBlackListEntity tokenToBlock = TokenBlackListEntity.builder()
                        .token(token)
                        .expireDate(Timestamp.from(tokenClaims.getBody().getExpiration().toInstant()))
                        .build();

                tokenBlackListRepository.save(tokenToBlock);

            } catch (Exception e) {
                log.debug("Invalid token: " + token);
            }
        }
    }

    private String generateAccessToken(UserEntity userEntity) {
        Date now = new Date();
        Date expirationDate = Date.from(now.toInstant().plus(jwtProperties.getAccessTokenValidityTime(), ChronoUnit.MILLIS));
        Map<String, Object> claims = new HashedMap<>();
        claims.put(ROLE_CLAIMS, List.of(ROLE_USER));
        return Jwts.builder()
                .setIssuer(applicationName)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .setSubject(userEntity.getUsername())
                .addClaims(claims)
                .signWith(HS512, jwtProperties.getAccessTokenSecret())
                .compact();
    }

    private String generateRefreshToken(UserEntity userEntity) {
        Date now = new Date();
        Date expirationDate = Date.from(now.toInstant().plus(jwtProperties.getRefreshTokenValidityTime(), ChronoUnit.MILLIS));
        return Jwts.builder()
                .setIssuer(applicationName)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .setSubject(userEntity.getUsername())
                .signWith(HS512, jwtProperties.getRefreshTokenSecret())
                .compact();
    }


}
