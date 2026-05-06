package Decorador;

import Factory.IMessage;
import Factory.Message;
import Observer.NotificationManager;
import Observer.NotificationStatus;

/**
 * Decorador de Notificación
 * 
 * Este decorador integra el patrón Observer con el Decorator.
 * Después de que el mensaje es procesado por el resto de la cadena,
 * dispara un evento de notificación.
 */
public class NotificationDecorador extends MessageDecorador {

    public NotificationDecorador(IMessage message) {
        super(message);
    }

    @Override
    public void procesar(Message msg) {
        // 1. Ejecutar el procesamiento (envío real)
        super.procesar(msg);

        // 2. Notificar a los observadores (Auditoría, Estadísticas, etc.)
        NotificationManager.getInstance().changeStatus(
            msg.getId(),
            msg.getType(),
            msg.getRecipient(),
            msg.getContenido(),
            NotificationStatus.ENVIADO
        );
    }
}
