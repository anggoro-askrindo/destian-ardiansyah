package id.co.test.test.service.service;

import id.co.test.test.dto.UpdateRolesRequestDto;
import id.co.test.test.dto.UserRoleRequestDto;
import id.co.test.test.dto.common.ResultDto;
import jakarta.servlet.http.HttpServletRequest;

public interface RoleService {

    ResultDto<Object> updateRoles(UpdateRolesRequestDto request, HttpServletRequest httpServletRequest);
    ResultDto<Object> setupRoles(UserRoleRequestDto request, HttpServletRequest httpServletRequest);
}
