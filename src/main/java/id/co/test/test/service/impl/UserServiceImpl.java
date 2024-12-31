package id.co.test.test.service.impl;

import id.co.test.test.dto.LoginRequestDto;
import id.co.test.test.dto.LoginResponseDto;
import id.co.test.test.dto.RegistrationRequestDto;
import id.co.test.test.dto.common.ResultDto;
import id.co.test.test.model.MasterUser;
import id.co.test.test.repository.UserRepository;
import id.co.test.test.service.service.UserService;
import id.co.test.test.util.ResponseUtil;
import id.co.test.test.util.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final TokenUtils tokenUtils;

    @Override
    public ResultDto<Object> registration(RegistrationRequestDto request, HttpServletRequest httpServletRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        try {
            TokenUtils.claimObjectValue(httpServletRequest, MasterUser.class);
            List<MasterUser> masterUserList = userRepository.findByUsernameOrEmailOrFullname(request.getUsername(), request.getEmail(), request.getFullname());
            if (!masterUserList.isEmpty()) {
                return ResponseUtil.success("02",
                        "registration",
                        "Username or Email or Fullname is exists",
                        null);
            }

            String hashedPassword = encoder.encode(request.getPassword());

            MasterUser masterUser = new MasterUser();
            masterUser.setUsername(request.getUsername());
            masterUser.setPassword(hashedPassword);
            masterUser.setFullname(request.getFullname());
            masterUser.setEmail(request.getEmail());
            masterUser.setCreatedBy("system");

            userRepository.save(masterUser);

            return ResponseUtil.success("00",
                    "registration",
                    "Success Registration",
                    null);

        } catch (Exception e) {
            log.info(e.getMessage()+" :Error");
            return ResponseUtil.success("02",
                    "registration",
                    "Unexpected Error",
                    null);
        }
    }

    @Override
    public ResultDto<Object> login(LoginRequestDto request, HttpServletRequest httpServletRequest) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        try {
            String token;
            MasterUser masterUser = userRepository.findByUsername(request.getUsername());
            if (Objects.isNull(masterUser)) {
                return ResponseUtil.success("02",
                        "login",
                        "Username is not match",
                        null);
            }

            if (encoder.matches(request.getPassword(), masterUser.getPassword())) {
                // generate token
                token = tokenUtils.generateToken(request.getUsername(), masterUser);
            } else {
                return ResponseUtil.success("02",
                        "login",
                        "Password is not match",
                        null);
            }

            if (Objects.nonNull(token)) {
                return ResponseUtil.success("00",
                        "login",
                        "Success login",
                        LoginResponseDto.builder()
                                .username(request.getUsername())
                                .token(token).build());
            } else {
                return ResponseUtil.success("02",
                        "login",
                        "Error login",
                        null);
            }

        } catch (Exception e) {
            log.info(e.getMessage()+" :Error");
            return ResponseUtil.success("02",
                    "registration",
                    "Unexpected Error",
                    null);
        }
    }
}
