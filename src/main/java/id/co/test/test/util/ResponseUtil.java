package id.co.test.test.util;

import id.co.test.test.dto.common.ResultDto;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {

    public static <T> ResultDto<T> success(String customCode,String codeSystem, String message,T result) {
        ResultDto<T> response = new ResultDto<>();
        response.setCode(customCode);
        response.setCodeSystem(codeSystem);
        response.setMessage(message);
        response.setResult(result);
        return response;
    }
}
