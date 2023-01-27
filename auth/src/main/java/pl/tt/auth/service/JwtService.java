package pl.tt.auth.service;

import pl.tt.auth.model.AuthenticationDTO;
import pl.tt.auth.model.LogoutDTO;
import pl.tt.auth.model.UsernamePasswordDTO;

public interface JwtService {
    AuthenticationDTO auth(UsernamePasswordDTO usernamePasswordDTO);

    void logout(LogoutDTO logoutDTO);
}
