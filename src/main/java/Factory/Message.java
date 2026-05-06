package Factory;

/**
 *
 * @author valeb
 */
public class Message {
    private String id;
    private String type;
    private String recipient;
    private String contenido;

    public Message() {
    }

    public Message(String id, String type, String recipient, String contenido) {
        this.id = id;
        this.type = type;
        this.recipient = recipient;
        this.contenido = contenido;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
