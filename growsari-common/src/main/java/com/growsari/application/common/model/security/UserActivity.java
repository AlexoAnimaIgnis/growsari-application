package com.growsari.application.common.model.security;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.growsari.application.util.SecurityConstants;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Proxy(lazy = false)
@Table(name = "USER_ACTIVITY")
@PrimaryKeyJoinColumn(name = "ACTIVITY_FK")
@JsonTypeName("com.growsari.application.common.model.security.UserActivity")
public class UserActivity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The primary key.
     */
    @Id
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "GROWSARI_USER_FK", nullable = false)
    private GrowsariUser growsariUser;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserActivityType type;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_FK", nullable = false)
    private ActivityCategory category;
    @ManyToMany(mappedBy = "activitySet")
    private Set<GrowsariUser> userSet = new LinkedHashSet<>();

    public UserActivity() {
    }

    public UserActivity(GrowsariUser growsariUser, UserActivityType type) {
        this.growsariUser = growsariUser;
        this.type = type;

        setName(String.format("%s_%s", "", growsariUser.getId()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GrowsariUser getGrowsariUser() {
        return growsariUser;
    }

    public void setGrowsariUser(GrowsariUser growsariUser) {
        this.growsariUser = growsariUser;
    }

    public UserActivityType getType() {
        return type;
    }

    public void setType(UserActivityType type) {
        this.type = type;
    }

    public ActivityCategory getCategory() {
        return category;
    }

    public void setCategory(ActivityCategory category) {
        this.category = category;
    }

    public Set<GrowsariUser> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<GrowsariUser> userSet) {
        this.userSet = userSet;
    }

    public String toAuthority() {
        return String.format("%s_%s", type.name(), growsariUser.getId());
    }
}
