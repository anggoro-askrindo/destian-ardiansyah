package id.co.test.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -735300519693409171L;

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String fullname;
    @NotNull
    private String email;
}
