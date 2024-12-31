package id.co.test.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonModel<PK>{

    protected static final int UUID_LENGTH = 36;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "createdDate", nullable = false)
    protected Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "modified_date")
    protected Date modifiedDate;

    @JsonProperty
    @CreatedBy
    @Column(name = "created_by", nullable = false)
    protected String createdBy;
    @JsonProperty
    @Column(name = "modified_by")
    protected String modifiedBy;
    @JsonProperty
    @Column(name = "version")
    protected Integer version = 0;
    @JsonProperty
    @Column(name = "is_active")
    protected boolean isActive;

    @PrePersist
    public void onCreate(){
        if (null == this.createdDate){
            this.createdDate = new Date();
        }
    }

    @PreUpdate
    public void onUpdate(){
        if (null == this.modifiedDate){
            this.modifiedDate = new Date();
        }
    }
}
