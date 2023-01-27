package pl.tt.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tt.auth.model.TokenBlackListEntity;

@Repository
public interface TokenBlackListRepository extends JpaRepository<TokenBlackListEntity, String> {

}
