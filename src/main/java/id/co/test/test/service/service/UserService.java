package id.co.test.test.service.service;

import id.co.test.test.dto.LoginRequestDto;
import id.co.test.test.dto.RegistrationRequestDto;
import id.co.test.test.dto.common.ResultDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    ResultDto<Object> registration(RegistrationRequestDto request, HttpServletRequest httpServletRequest);
    ResultDto<Object> login(LoginRequestDto request, HttpServletRequest httpServletRequest);
}
