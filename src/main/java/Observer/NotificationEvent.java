package Observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 
/**
 * Objeto de evento que el notificador pasa a los suscriptores.
 * Contiene toda la información contextual del cambio de estado.
 * Solo encapsula datos del evento.
 */
public class NotificationEvent {
 
    private final String notificationId;
    private final String type;       // EMAIL, SMS, PUSH
    private final String recipient;
    private final String message;
    private final NotificationStatus status;
    private final LocalDateTime timestamp;
 
    public NotificationEvent(String notificationId, String type,
                             String recipient, String message,
                             NotificationStatus status) {
        this.notificationId = notificationId;
        this.type           = type;
        this.recipient      = recipient;
        this.message        = message;
        this.status         = status;
        this.timestamp      = LocalDateTime.now();
    }
 
    public String getNotificationId() { return notificationId; }
    public String getType()           { return type; }
    public String getRecipient()      { return recipient; }
    public String getMessage()        { return message; }
    public NotificationStatus getStatus() { return status; }
    public LocalDateTime getTimestamp()   { return timestamp; }
 
    public String getFormattedTimestamp() {
        return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
 
    @Override
    public String toString() {
        return String.format("[%s] [%s] ID=%s | Para: %s | Estado: %s | Msg: %s",
                getFormattedTimestamp(), type, notificationId, recipient, status, message);
    }
}
