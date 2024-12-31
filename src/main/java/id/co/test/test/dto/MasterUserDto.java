package id.co.test.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MasterUserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1881839213311250316L;

    private String username;
    private String fullname;
    private String email;
    private UUID idUser;
}
