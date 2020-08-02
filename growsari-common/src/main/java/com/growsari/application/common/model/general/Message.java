package com.growsari.application.common.model.general;

import com.growsari.application.common.model.GrowsariModifiableEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * @author alexander.ballester
 */

@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "MESSAGE")
@NamedQueries({
        @NamedQuery(name = Message.QUERY_FIND_BY_TOPIC, query = "SELECT b FROM Message b WHERE b.topic.id = :"
                + Message.QUERY_PARAM_TOPIC_ID
        )
})
public class Message extends GrowsariModifiableEntity {
    private static final long serialVersionUID = 1L;

    public static final String QUERY_FIND_BY_TOPIC = "Message.findByTopic";
    public static final String QUERY_PARAM_TOPIC_ID = "topicId";

    @ManyToOne(optional = false)
    @JoinColumn(name = "TOPIC_FK", nullable = false)
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
