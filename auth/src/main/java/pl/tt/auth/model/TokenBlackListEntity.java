package pl.tt.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Builder
@Entity
@Table(name="token_blacklist")
@NoArgsConstructor
@AllArgsConstructor
public class TokenBlackListEntity {

    @Id
    @Column(name = "token", length = 500)
    private String token;

    @Column(name="expire_date")
    private Timestamp expireDate;

}
