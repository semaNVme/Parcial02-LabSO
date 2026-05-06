package Observer;
 
/*
 * Interfaz Suscriptora
 *
 * Esta interfaz declara únicamente el método necesario para recibir notificaciones.
 * Nuevos observadores pueden agregarse sin modificar el código existente.
 */
public interface NotificationObserver {
 
    /**
     * Método que el notificador invoca cuando ocurre un cambio de estado.
     */
    void update(NotificationEvent event);
}