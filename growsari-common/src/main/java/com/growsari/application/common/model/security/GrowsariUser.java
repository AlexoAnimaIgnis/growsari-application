package com.growsari.application.common.model.security;

import com.growsari.application.common.model.GrowsariAbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.util.Objects;

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
