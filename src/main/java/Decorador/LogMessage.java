package Decorador;

/**
 *
 * @author valeb
 */
import Factory.IMessage;
import Factory.Message;

public class LogMessage extends MessageDecorador {

    public LogMessage(IMessage message) {
        super(message);
    }

    @Override
    public void procesar(Message msg) {
        System.out.println("Logging: Procesando mensaje -> " + msg.getContenido());
        super.procesar(msg);
    }
}
