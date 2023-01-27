package pl.tt.auth.service;

import pl.tt.auth.model.UserEntity;

public interface UserService {

    UserEntity findByUsername(String username);
}
