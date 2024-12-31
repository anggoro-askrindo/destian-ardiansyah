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
public class LoginResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 8735084217367755318L;

    private String username;
    private String token;
}
