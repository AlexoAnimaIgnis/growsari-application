package com.growsari.application.common.model.general;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.growsari.application.common.model.GrowsariModifiableEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author alexander.ballester
 */
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TOPIC")
public class Topic extends GrowsariModifiableEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "SUBJECT", unique = true, nullable = false)
    private String subject;

    @Column(name = "DESCRIPTION")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Message> messageList = new LinkedHashSet<>();

    public Topic() {}

    public Topic(String subject, String description) {
        this.subject = subject;
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(Set<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int hashCode() {
        return subject != null ? subject.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Topic that = (Topic) obj;

        return Objects.equals(subject, that.subject);
    }

    @Override
    public String toString() {
        return "SUBJECT: " + subject + ";"
                + " DESCRIPTION: " + description;
    }
}
