package com.growsari.application.common.model.security;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ACTIVITY_CATEGORY")
public class ActivityCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    public ActivityCategory() {
    }

    public ActivityCategory(ActivityCategoryName name) {
        this.name = name;
    }

    /**
     * The primary key.
     */
    @Id
    @Column(name = "NAME", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private ActivityCategoryName name;

    @Column(name = "DESCRIPTION")
    private String description;

    public ActivityCategoryName getName() {
        return name;
    }

    public void setName(ActivityCategoryName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}