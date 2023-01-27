package pl.tt.gateway.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.tt.gateway.auth.model.AuthDTO;

@FeignClient(value = "auth")
public interface AuthFeignClient {

	@RequestMapping(method = RequestMethod.POST, path = "/auth")
	ResponseEntity<String> getAuthorizationToken(@RequestBody AuthDTO authDTO);
}
