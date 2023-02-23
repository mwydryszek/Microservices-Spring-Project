package pl.tt.gateway.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.tt.gateway.auth.model.AccessTokenDTO;
import pl.tt.gateway.auth.model.AuthDTO;
import pl.tt.gateway.auth.model.CheckTokenDTO;

@FeignClient(value = "auth")
public interface AuthFeignClient {

	@RequestMapping(method = RequestMethod.POST, path = "/auth")
	ResponseEntity<String> getAuthorizationToken(@RequestBody AuthDTO authDTO);

	@RequestMapping(method = RequestMethod.POST, path = "/auth/checkToken")
	ResponseEntity<CheckTokenDTO> getAuthorizationToken(@RequestBody AccessTokenDTO accessToken);

	@RequestMapping(method = RequestMethod.POST, path = "/auth/test")
	ResponseEntity<CheckTokenDTO> checkToken(AccessTokenDTO build);
}
