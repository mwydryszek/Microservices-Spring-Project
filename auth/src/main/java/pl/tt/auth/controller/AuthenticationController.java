package pl.tt.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tt.auth.model.AuthenticationDTO;
import pl.tt.auth.model.LogoutDTO;
import pl.tt.auth.model.UsernamePasswordDTO;
import pl.tt.auth.service.JwtService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final JwtService jwtService;

	@PostMapping
	public ResponseEntity<AuthenticationDTO> authenticate(@RequestBody UsernamePasswordDTO usernamePasswordDTO){
		return ResponseEntity.ok(jwtService.auth(usernamePasswordDTO));
	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logout(@RequestBody LogoutDTO logoutDTO){
		jwtService.logout(logoutDTO);
		return ResponseEntity.ok().build();
	}

}
