package id.co.test.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "r_user_roles",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"role_name", "id_user"})})
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoles implements Serializable {


    @Serial
    private static final long serialVersionUID = 106770569230477477L;

    @EmbeddedId
    private UserRolesComposite id;
}
