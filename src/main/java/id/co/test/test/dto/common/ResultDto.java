package id.co.test.test.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto<T> extends StatusResponseDto implements Serializable {

    private static final long serialVersionUID = 7360383938026132110L;

    @JsonProperty(index = 4)
    private T result;

}
