package id.co.test.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "m_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MasterRole extends CommonModel implements Serializable {

    @Id
    @JsonProperty
    @Column(name = "id_role", length = UUID_LENGTH)
    private UUID idRole = UUID.fromString(UUID.randomUUID().toString());

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "keterangan")
    private String keterangan;
}
