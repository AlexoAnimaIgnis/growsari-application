package com.growsari.application.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author alexander.ballester
 */
@MappedSuperclass
public abstract class GrowsariModifiableEntity extends GrowsariAbstractEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "MODIFICATION_ID", nullable = false)
    protected Integer modificationId;

    /**
     * responsible for creation
     */
    @Column(name = "CREATED_BY")
    private String createdBy;

    /**
     * responsible for modification
     */
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    /**
     * deleted date
     */
    @Column(name = "DELETED_AT", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deletedAt;

    public Integer getModificationId() {
        return modificationId;
    }

    public void setModificationId(Integer modificationId) {
        this.modificationId = modificationId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
