package Observer;
/**
 * Estados posibles de una notificación
 * Permite al Observer transportar información contextual del evento.
 */
public enum NotificationStatus {
    PENDIENTE("Pendiente"),
    ENVIADO("Enviado"),
    FALLIDO("Fallido");
 
    private final String label;
 
    NotificationStatus(String label) {
        this.label = label;
    }
 
    @Override
    public String toString() {
        return label;
    }
}
