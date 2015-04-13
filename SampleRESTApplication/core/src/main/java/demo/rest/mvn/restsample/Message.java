package demo.rest.mvn.restsample;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {

    private Date created;
    private String message;
    private int uniqueId;

    public Message() {
    }

    public Message(Date created, String message, int uniqueId) {
        this.created = created;
        this.message = message;
        this.uniqueId = uniqueId;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "<span class='created'>CREATED: " + created + "</span> <span class='uniqueId'>ID: " + uniqueId + "</span> <span class='message'>MESSAGE: " + message + "</span>"; //NOI18N
    }
}
