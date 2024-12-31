package id.co.test.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@Embeddable
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesComposite implements Serializable {

    @Serial
    private static final long serialVersionUID = 6456916183893649129L;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "id_user", nullable = false)
    private UUID idUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRolesComposite that = (UserRolesComposite) o;
        return Objects.equals(roleName, that.roleName) &&
               Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName, idUser);
    }
}
