package Decorador;

/**
 *
 * @author valeb
 */
import Factory.IMessage;
import Factory.Message;

public class ValidateMessage extends MessageDecorador {

    public ValidateMessage(IMessage message) {
        super(message);
    }

    @Override
    public void procesar(Message msg) {
        if (msg.getContenido() == null || msg.getContenido().trim().isEmpty()) {
            System.out.println("Validacion Fallida: El mensaje esta vacio.");
        } else {
            System.out.println("Validacion Exitosa: Mensaje valido.");
            super.procesar(msg);
        }
    }
}
