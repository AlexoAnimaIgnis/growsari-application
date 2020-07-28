package com.growsari.application.common.model.general;

import com.growsari.application.common.model.ModifiableEntity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

/**
 * @author alexander.ballester
 */
public class Message extends ModifiableEntity {
    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false)
    @JoinColumn(name = "TOPIC_FK", nullable = false)
    @Valid
    private Topic topic;

    @Column(name = "MESSAGE")
    private String message;

    public Message() {}

    public Message(String message, Topic topic) {
        this.topic = topic;
        this.message = message;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "MESSAGE: " + message;
    }
}
