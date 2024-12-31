package id.co.test.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serial;
import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 5397680956937944185L;

    @NotNull
    private String roleName;
    @NotNull
    private String idUser;
}