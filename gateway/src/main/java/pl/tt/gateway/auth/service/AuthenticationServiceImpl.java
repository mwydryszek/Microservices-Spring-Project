package pl.tt.gateway.auth.service;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import pl.tt.gateway.auth.feign.AuthFeignClient;
import pl.tt.gateway.auth.model.AuthDTO;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AuthFeignClient authFeignClient;

	//@PostConstruct
	@Override
	public void authenticate() {
		System.out.println(authFeignClient.getAuthorizationToken(new AuthDTO("a", "b")));
	}
}
