package id.co.test.test.repository;

import id.co.test.test.model.UserRoles;
import id.co.test.test.model.UserRolesComposite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, UserRolesComposite> {

    @Query(value = "SELECT rur.* FROM r_user_roles rur WHERE rur.role_name = ?1 AND rur.id_user = ?2", nativeQuery = true)
    UserRoles getDataByRoleNameAndIdUser(String roleName, UUID idUser);
}
