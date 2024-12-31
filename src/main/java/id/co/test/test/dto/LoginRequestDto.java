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
public class LoginRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 4461172209208967022L;

    @NotNull
    private String username;
    @NotNull
    private String password;
}
