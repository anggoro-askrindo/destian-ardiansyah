package id.co.test.test.repository;

import id.co.test.test.model.MasterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<MasterUser, String> {

    List<MasterUser> findByUsernameOrEmailOrFullname(String username, String email, String fullname);
    MasterUser findByUsername(String username);

    @Query(value = "SELECT rur.* FROM m_user rur WHERE rur.id_user = ?1", nativeQuery = true)
    MasterUser findByIdUser(UUID idUser);
}
