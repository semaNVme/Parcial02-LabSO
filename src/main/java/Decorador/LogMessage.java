package Decorador;

/**
 *
 * @author valeb
 */
import Factory.IMessage;

public class LogMessage extends MessageDecorador {

    public LogMessage(IMessage message) {
        super(message);
    }

    @Override
    public void procesar(String mess) {
        System.out.println("Logging: Procesando mensaje -> " + mess);
        super.procesar(mess);
    }
}
