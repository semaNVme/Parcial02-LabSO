package Factory;

/**
 *
 * @author valeb
 */
public class Message {
    int id;
    String contenido;

    public Message() {
    }

    public Message(int id, String contenido) {
        this.id = id;
        this.contenido = contenido;
    }
    
    public int getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
