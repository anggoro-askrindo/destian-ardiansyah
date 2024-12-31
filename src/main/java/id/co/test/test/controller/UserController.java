package id.co.test.test.controller;

import id.co.test.test.dto.LoginRequestDto;
import id.co.test.test.dto.RegistrationRequestDto;
import id.co.test.test.dto.UpdateRolesRequestDto;
import id.co.test.test.dto.common.ResultDto;
import id.co.test.test.service.service.UserService;
import id.co.test.test.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private static Logger log = LogManager.getLogger(UserController.class);
    private final UserService userService;

    @PostMapping("/registration")
    public ResultDto<Object> registration(@RequestBody RegistrationRequestDto request, HttpServletRequest httpServletRequest) {
        try {
            return userService.registration(request, httpServletRequest);
        } catch (Exception e) {
            log.info("Error Authentication ", e.getStackTrace());
            return ResponseUtil.success("02",
                    "registration",
                    "Unexpected Error",
                    null);
        }
    }

    @PostMapping("/login")
    public ResultDto<Object> login(@RequestBody LoginRequestDto request, HttpServletRequest httpServletRequest) {
        try {
            return userService.login(request, httpServletRequest);
        } catch (Exception e) {
            log.info("Error Authentication ", e.getStackTrace());
            return ResponseUtil.success("02",
                    "login",
                    "Unexpected Error",
                    null);
        }
    }
}
