package id.co.test.test.repository;

import id.co.test.test.model.MasterRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository  extends JpaRepository<MasterRole, String> {

    MasterRole findByRoleName(String roleName);

    @Query(value = "SELECT rur.* FROM m_role rur WHERE rur.id_role = ?1", nativeQuery = true)
    MasterRole findByIdRole(UUID idRole);
}
