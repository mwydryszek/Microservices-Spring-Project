package pl.tt.auth;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.tt.auth.model.UserEntity;
import pl.tt.auth.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserCreationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initUser(){

        userRepository.deleteAll();

        UserEntity userEntity = UserEntity.builder()
                .active(1)
                .username("Sydnej")
                .email("filipbos1997@o2.pl")
                .password(passwordEncoder.encode("qwerty123"))
                .build();

        userRepository.save(userEntity);

    }

}
