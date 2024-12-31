package id.co.test.test.controller;

import id.co.test.test.dto.UpdateRolesRequestDto;
import id.co.test.test.dto.UserRoleRequestDto;
import id.co.test.test.dto.common.ResultDto;
import id.co.test.test.service.service.RoleService;
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
@RequestMapping("/role")
@RequiredArgsConstructor
public class RolesController {

    private static Logger log = LogManager.getLogger(RolesController.class);
    private final RoleService roleService;

    @PostMapping("/update")
    public ResultDto<Object> updateRoles(@RequestBody UpdateRolesRequestDto request, HttpServletRequest httpServletRequest) {
        try {
            return roleService.updateRoles(request, httpServletRequest);
        } catch (Exception e) {
            log.info("Error Authentication ", e.getStackTrace());
            return ResponseUtil.success("02",
                    "registration",
                    "Unexpected Error",
                    null);
        }
    }
    @PostMapping("/setup/role")
    public ResultDto<Object> setupRoles(@RequestBody UserRoleRequestDto request, HttpServletRequest httpServletRequest) {
        try {
            return roleService.setupRoles(request, httpServletRequest);
        } catch (Exception e) {
            log.info("Error Authentication ", e.getStackTrace());
            return ResponseUtil.success("02",
                    "registration",
                    "Unexpected Error",
                    null);
        }
    }
}
