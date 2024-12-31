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
public class UpdateRolesRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -2546872388503675642L;

    private String idRoles;
    @NotNull
    private String roleName;
    @NotNull
    private String keterangan;
}
