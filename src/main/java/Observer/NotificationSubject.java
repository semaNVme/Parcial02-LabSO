package Observer;
 
/**
 * Interfaz Notificadora
 *
 * Los módulos dependen de esta asbtracción, desacopla completamente a los observadores del sujeto concreto.
 */
public interface NotificationSubject {
 
    /** Registra un observador en la lista de suscriptores. */
    void addObserver(NotificationObserver observer);
 
    /** Elimina un observador de la lista de suscriptores. */
    void removeObserver(NotificationObserver observer);
 
    /** Recorre la lista y notifica a todos los suscriptores registrados. */
    void notifyObservers();
}