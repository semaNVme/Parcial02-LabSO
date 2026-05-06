package Observer;
import java.util.ArrayList;
import java.util.List;

 
/**
 *
 * Gestiona la lista de suscriptores y notifica cambios de estado.
 * Nuevos observadores se agregan sin
 * modificar esta clase.
 */
public class NotificationManager implements NotificationSubject {
 
    /** Lista de suscriptores registrados (módulo auditoría, estadísticas, UI…) */
    private final List<NotificationObserver> observers = new ArrayList<>();
 
    /** Último evento generado; lo leen los observadores al recibir update(). */
    private NotificationEvent lastEvent;
 
    // ── Métodos de suscripción ──────────────────────────────────────────────
 
    @Override
    public void addObserver(NotificationObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
 
    @Override
    public void removeObserver(NotificationObserver observer) {
        observers.remove(observer);
    }
 
    /**
     * Recorre la lista de suscriptores e invoca update() en cada uno,
     * pasando el evento actual como información contextual.
     */
    @Override
    public void notifyObservers() {
        for (NotificationObserver observer : observers) {
            observer.update(lastEvent);
        }
    }
 
    // ── Lógica de negocio ───────────────────────────────────────────────────
 
    /**
     * Registra un cambio de estado en una notificación y dispara la cadena
     * de observadores. Evita el acoplamiento directo: el cliente sólo llama
     * este método; los módulos reaccionan por suscripción.
     */
    public void changeStatus(String id, String type, String recipient,
                             String message, NotificationStatus status) {
        this.lastEvent = new NotificationEvent(id, type, recipient, message, status);
        notifyObservers();
    }
 
    public NotificationEvent getLastEvent() {
        return lastEvent;
    }
}
 
