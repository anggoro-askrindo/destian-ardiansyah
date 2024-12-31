package id.co.test.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "m_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterUser extends CommonModel implements Serializable {

    @Id
    @JsonProperty
    @Column(name = "id_user", length = UUID_LENGTH)
    private UUID idUser = UUID.fromString(UUID.randomUUID().toString());

    @Column(name = "account_expired")
    private boolean accountExpired = false;

    @Column(name = "account_locked")
    private boolean accountLocked = false;

    @Column(name = "credential_is_expired")
    private boolean credentialIsExpired = false;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "login_attempt")
    private Integer loginAttempt = 0;
}
