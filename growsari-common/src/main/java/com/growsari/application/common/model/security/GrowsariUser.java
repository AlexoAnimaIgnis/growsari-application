package com.growsari.application.common.model.security;

import com.growsari.application.common.model.GrowsariAbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author alexander.ballester
 */
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "GROWSARIUSER")
public class GrowsariUser extends GrowsariAbstractEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL", nullable = false)
    @Email
    private String email;

    @ManyToMany
    @JoinTable(name = "GROWSARI_USER_ACTIVITY",
            joinColumns = {@JoinColumn(name = "GROWSARI_USER_FK", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "ACTIVITY_FK", nullable = false, updatable = false)}
    )
    private Set<UserActivity> activitySet = new LinkedHashSet<>();

    public GrowsariUser() {}

    public GrowsariUser(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserActivity> getActivitySet() {
        return activitySet;
    }

    public void setActivitySet(Set<UserActivity> activitySet) {
        this.activitySet = activitySet;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        GrowsariUser that = (GrowsariUser) obj;

        return Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return "USER: " + getName() + ";"
                + "EMAIL: " + getEmail();
    }
}
