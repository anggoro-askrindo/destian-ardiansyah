package id.co.test.test.service.impl;

import id.co.test.test.dto.UpdateRolesRequestDto;
import id.co.test.test.dto.UserRoleRequestDto;
import id.co.test.test.dto.common.ResultDto;
import id.co.test.test.model.MasterRole;
import id.co.test.test.model.UserRoles;
import id.co.test.test.model.UserRolesComposite;
import id.co.test.test.repository.RoleRepository;
import id.co.test.test.repository.UserRepository;
import id.co.test.test.repository.UserRolesRepository;
import id.co.test.test.service.service.RoleService;
import id.co.test.test.util.ResponseUtil;
import id.co.test.test.util.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;
    @Autowired
    private UserRepository userRepository;

    private final TokenUtils tokenUtils;

    @Override
    public ResultDto<Object> updateRoles(UpdateRolesRequestDto request, HttpServletRequest httpServletRequest) {

        try {
            if (Objects.isNull(request.getIdRoles())) {
                var masterRoleExist = roleRepository.findByRoleName(request.getRoleName());
                if (Objects.isNull(masterRoleExist)) {
                    MasterRole masterRole = new MasterRole();
                    masterRole.setRoleName(request.getRoleName());
                    masterRole.setKeterangan(request.getKeterangan());
                    masterRole.setCreatedBy("system");

                    roleRepository.save(masterRole);
                    return ResponseUtil.success("00",
                            "roles service",
                            "succes add new roles",
                            null);
                } else {
                    return ResponseUtil.success("02",
                            "roles service",
                            "Role name is exists",
                            null);
                }
            } else {
                var masterRoleExist = roleRepository.findByIdRole(UUID.fromString(request.getIdRoles()));
                if (Objects.nonNull(masterRoleExist)) {
                    var masterRoleExistCheckedRole = roleRepository.findByRoleName(request.getRoleName());
                    if (Objects.nonNull(masterRoleExistCheckedRole) && !masterRoleExistCheckedRole.getIdRole().equals(request.getIdRoles())) {
                        return ResponseUtil.success("02",
                                "roles service",
                                "Role name is exists",
                                null);
                    } else {
                        masterRoleExist.setRoleName(request.getRoleName());
                        masterRoleExist.setKeterangan(request.getKeterangan());

                        roleRepository.save(masterRoleExist);

                        return ResponseUtil.success("00",
                                "roles service",
                                "Success update role",
                                null);
                    }
                } else {
                    return ResponseUtil.success("02",
                            "roles service",
                            "Role name is exists",
                            null);
                }
            }

        } catch (Exception e) {
            log.info(e.getStackTrace()+" :Error");
            return ResponseUtil.success("02",
                    "roles service",
                    "Unexpected Error",
                    null);
        }
    }

    @Override
    public ResultDto<Object> setupRoles(UserRoleRequestDto request, HttpServletRequest httpServletRequest) {
        try {
            var role = roleRepository.findByRoleName(request.getRoleName());
            var user = userRepository.findByIdUser(UUID.fromString(request.getIdUser()));

            if (Objects.nonNull(role) && Objects.nonNull(user)) {
                var userRoles = userRolesRepository.getDataByRoleNameAndIdUser(request.getRoleName(), UUID.fromString(request.getIdUser()));
                if (Objects.isNull(userRoles)) {
                    UserRolesComposite userRolesComposite = new UserRolesComposite();
                    userRolesComposite.setRoleName(request.getRoleName());
                    userRolesComposite.setIdUser(UUID.fromString(request.getIdUser()));

                    UserRoles userRoless = new UserRoles();
                    userRoless.setId(userRolesComposite);

                    userRolesRepository.save(userRoless);
                    return ResponseUtil.success("00",
                            "roles service",
                            "success setup roles and user",
                            null);
                }
                return ResponseUtil.success("02",
                        "roles service",
                        "role and user have setup exists",
                        null);
            } else {
                return ResponseUtil.success("02",
                        "roles service",
                        "role Or user dont have exists",
                        null);
            }

        } catch (Exception e) {
            log.info(e.getMessage()+" :Error");
            return ResponseUtil.success("02",
                    "roles service",
                    "Unexpected Error",
                    null);
        }
    }
}
