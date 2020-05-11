package faber.codetest.nhat.entity;

/*
   This class handles commons attributes of entity-objects which are mapped by to Database
 */

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long createdTs;

    @Column
    private Long updatedTs;

    @PrePersist
    public void prePersist() {
        this.createdTs = System.currentTimeMillis();
        this.updatedTs = System.currentTimeMillis();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedTs = System.currentTimeMillis();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(Long createdTs) {
        this.createdTs = createdTs;
    }

    public Long getUpdatedTs() {
        return updatedTs;
    }

    public void setUpdatedTs(Long updatedTs) {
        this.updatedTs = updatedTs;
    }
}
